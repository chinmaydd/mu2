package chinmaydd.mu2.ui;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.android.volley.ExecutorDelivery;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

//import org.json.JSONArray;
import org.json.JSONObject;

import chinmaydd.mu2.R;
import chinmaydd.mu2.ui.base.BaseActivity;
import chinmaydd.mu2.ui.quote.ArticleDetailActivity;
import chinmaydd.mu2.ui.quote.ArticleDetailFragment;
import chinmaydd.mu2.ui.quote.ArticleListFragment;
import chinmaydd.mu2.ui.quote.Global;
import chinmaydd.mu2.ui.quote.ListActivity;
//import search_results;

public class PostActivity extends BaseActivity implements ArticleListFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupToolbar();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Button button = (Button) findViewById(R.id.post);
        final String url = "http://mu2.herokuapp.com/posts/";
        final String username = Global.UserName;
        final String email = Global.Email;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent myIntent = new Intent(PostActivity.this,
                        ListActivity.class);

                EditText title = (EditText) findViewById(R.id.title);
                EditText desc = (EditText) findViewById(R.id.desc);
                EditText location = (EditText) findViewById(R.id.location);

                JSONObject params = new JSONObject();
                try {
                    params.put("title", title.getText().toString());
                    params.put("description", desc.getText().toString());
                    params.put("email", email);
                    params.put("user", username);
                    params.put("location", location.getText().toString());

                    Log.d("check params", params.toString());

                } catch(Exception e) {
                    e.printStackTrace();
                }
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsobj = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            myIntent.putExtra("status", "success");
                            Log.i("here", response.toString());
                            startActivity(myIntent);
                        } catch(Exception e) {
                            Log.i("Error", "Error");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                    }
                });
                queue.add(jsobj);
//              myIntent.putExtra("message", txt.getText().toString()
            }
        });
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemSelected(String id) {
        // Start the detail activity in single pane mode.
        Intent detailIntent = new Intent(this, ArticleDetailActivity.class);
        detailIntent.putExtra(ArticleDetailFragment.ARG_ITEM_ID, id);
        startActivity(detailIntent);
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