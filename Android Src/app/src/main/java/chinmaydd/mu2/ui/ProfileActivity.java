package chinmaydd.mu2.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import chinmaydd.mu2.R;
import chinmaydd.mu2.ui.base.BaseActivity;
import chinmaydd.mu2.ui.quote.Global;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String url = "http://172.20.10.2:3000/users/" + Global.Email.split("@")[0];
        Log.d("url", url);
        Ion.with(this)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {
                        } catch (Exception err) {
                            Log.i("Error", err.toString());
                        }
                    }
                });

        setupToolbar();
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
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
        return R.id.nav_profile;
    }
}
