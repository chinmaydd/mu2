package chinmaydd.mu2.ui.quote;

import android.os.Bundle;

import org.json.JSONArray;

import chinmaydd.mu2.R;
import chinmaydd.mu2.ui.base.BaseActivity;

public class ViewProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        // Show the Up button in the action bar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewProfileFragment fragment =  ViewProfileFragment.newInstance(getIntent().getStringExtra(ViewProfileFragment.ARG_ITEM_ID));
        getFragmentManager().beginTransaction().replace(R.id.profile_detail_container, fragment).commit();
    }

    @Override
    public boolean providesActivityToolbar() {
        return false;
    }
}
