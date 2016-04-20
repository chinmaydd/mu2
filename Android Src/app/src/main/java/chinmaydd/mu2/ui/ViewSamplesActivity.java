package chinmaydd.mu2.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import chinmaydd.mu2.R;
import chinmaydd.mu2.ui.base.BaseActivity;
import chinmaydd.mu2.ui.quote.ArticleDetailActivity;
import chinmaydd.mu2.ui.quote.ArticleDetailFragment;
import chinmaydd.mu2.ui.quote.ArticleListFragment;
import chinmaydd.mu2.ui.quote.search_results;

public class ViewSamplesActivity extends BaseActivity implements ArticleListFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        Button button = (Button) findViewById(R.id.search);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewSamplesActivity.this,
                        search_results.class);
                EditText txt = (EditText) findViewById(R.id.editText2);

                myIntent.putExtra("message", txt.getText().toString());
                startActivity(myIntent);
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
        return R.id.nav_search;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
