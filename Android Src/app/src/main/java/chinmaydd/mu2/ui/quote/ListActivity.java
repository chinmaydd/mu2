package chinmaydd.mu2.ui.quote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import chinmaydd.mu2.R;
import chinmaydd.mu2.dummy.DummyContent;
import chinmaydd.mu2.ui.PostActivity;
import chinmaydd.mu2.ui.base.BaseActivity;
import chinmaydd.mu2.util.LogUtil;

/**
 * Lists all available quotes. This Activity supports a single pane (= smartphones) and a two pane mode (= large screens with >= 600dp width).
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class ListActivity extends BaseActivity implements ArticleListFragment.Callback {
    /**
     * Whether or not the activity is running on a device with a large screen
     */
    private boolean twoPaneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final String newString;

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString = null;
            } else {
                newString = extras.getString("Success");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("Success");
        }

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.add_posts);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ListActivity.this,
                        PostActivity.class);
                startActivity(myIntent);
            }
        });

        DummyContent.ITEMS.clear();

//        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:3000/posts/";

        Ion.with(this)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {
                            Log.i("here", result.toString());
                            JsonArray j = result.getAsJsonArray("posts");

                            for (int i = 0; i < j.size(); i++) {
                                JsonObject json_data = j.get(i).getAsJsonObject();
                                JsonObject user_data = json_data.get("user").getAsJsonObject();
                                DummyContent.ITEMS.add(new DummyContent.DummyItem(Integer.toString(i), R.drawable.p1, json_data.get("title").getAsString(), user_data.get("name").getAsString(), json_data.get("description").getAsString()));
                            }

                            if(newString!= null && !newString.equals("True")) {
                                Intent intent = getIntent();
                                intent.putExtra("Success", "True");
                                finish();
                                startActivity(intent);
                            }
                        } catch (Exception err) {
                            Log.i("Error", err.toString());
                        }
                    }
                });

        ////////////////////////////////////////// Future Requests ////////////////////////////////////////////
//        RequestFuture<JSONObject> future = RequestFuture.newFuture();
//        int REQUEST_TIMEOUT = 5;
//        JsonObjectRequest jsobj = new JsonObjectRequest(Request.Method.GET, url, null, future, future);
//        queue.add(jsobj);
//
//        try {
//            JSONObject response = future.get(REQUEST_TIMEOUT, TimeUnit.SECONDS);
//            JSONArray jArray = response.getJSONArray("posts");
//
//            for(int i=0;i<jArray.length(); i++) {
//                JSONObject json_data = jArray.getJSONObject(i);
//                        Log.i("See data: ", json_data.toString());
//                JSONObject user_data = json_data.getJSONObject("user");
//                DummyContent.ITEMS.add(new DummyContent.DummyItem(Integer.toString(i), R.drawable.p1, json_data.getString("title"), user_data.getString("name"), json_data.getString("description")));
//            }
//        } catch (InterruptedException e) {
//        } catch (ExecutionException e) {
//        } catch (JSONException e) {
//        } catch (TimeoutException e) {
//        }
//


        /////////////////////////////////// Volley Reqests ////////////////////////////////////////////////////////////////////
//        JsonObjectRequest jsobj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
////                    Log.i("here", response.toString());
//                    JSONArray jArray = response.getJSONArray("posts");
//
//                    for(int i=0;i<jArray.length(); i++) {
//                        JSONObject json_data = jArray.getJSONObject(i);
////                        Log.i("See data: ", json_data.toString());
//                        JSONObject user_data = json_data.getJSONObject("user");
//                        DummyContent.ITEMS.add(new DummyContent.DummyItem(Integer.toString(i), R.drawable.p1, json_data.getString("title"), user_data.getString("name"),json_data.getString("description")));
//                    }
//                } catch(Exception e) {
//                    Log.i("Error", e.toString());
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//        queue.add(jsobj);


        setupToolbar();

        if (isTwoPaneLayoutUsed()) {
            twoPaneMode = true;
            LogUtil.logD("TEST","TWO POANE TASDFES");
            enableActiveItemState();
        }

        if (savedInstanceState == null && twoPaneMode) {
            setupDetailFragment();
        }
    }

    /**
     * Called when an item has been selected
     *
     * @param id the selected quote ID
     */
    @Override
    public void onItemSelected(String id) {
        if (twoPaneMode) {
            // Show the quote detail information by replacing the DetailFragment via transaction.
            ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(id);
            getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
        } else {
            // Start the detail activity in single pane mode.
            Intent detailIntent = new Intent(this, ArticleDetailActivity.class);
            detailIntent.putExtra(ArticleDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetailFragment() {
        ArticleDetailFragment fragment =  ArticleDetailFragment.newInstance(DummyContent.ITEMS.get(0).id);
        getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
    }

    /**
     * Enables the functionality that selected items are automatically highlighted.
     */
    private void enableActiveItemState() {
        ArticleListFragment fragmentById = (ArticleListFragment) getFragmentManager().findFragmentById(R.id.article_list);
        fragmentById.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    /**
     * Is the container present? If so, we are using the two-pane layout.
     *
     * @return true if the two pane layout is used.
     */
    private boolean isTwoPaneLayoutUsed() {
        return findViewById(R.id.article_detail_container) != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_quotes;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
