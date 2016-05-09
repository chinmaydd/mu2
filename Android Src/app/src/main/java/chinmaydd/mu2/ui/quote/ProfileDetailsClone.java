package chinmaydd.mu2.ui.quote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import chinmaydd.mu2.R;

public class ProfileDetailsClone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details_clone);

        Spinner dropdown = (Spinner)findViewById(R.id.user_type);
        String[] items = new String[]{"User", "Musician", "Band"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url;
                JSONObject json = new JSONObject();

                EditText location = (EditText) findViewById(R.id.profile_location);
                Spinner user_type = (Spinner)findViewById(R.id.user_type);
                EditText description = (EditText) findViewById(R.id.desc);
                EditText instrument = (EditText) findViewById(R.id.instrument);

                url = "http://mu2.herokuapp.com/users/";

                try {
                    if (user_type.getSelectedItem().toString().equals("User")) {
                        url = "http://mu2.herokuapp.com/users/";
                    } else if (user_type.getSelectedItem().toString().equals("Musician")) {
                        json.put("location", location.getText().toString());
                        json.put("description", description.getText().toString());
                        json.put("instrument", instrument.getText().toString());
                        url = "http://mu2.herokuapp.com/musicians/";
                    } else {
                        json.put("location", location.getText().toString());
                        json.put("description", description.getText().toString());
                        json.put("instrument", instrument.getText().toString());
                        url = "http://mu2.herokuapp.com/bands/";
                    }

                    json.put("name", Global.UserName);
                    json.put("email", Global.Email);
                } catch(JSONException e) {
                    Log.i("hello", "world");
                }
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsobj = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("hello", "world");
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

                Intent myIntent = new Intent(ProfileDetailsClone.this,
                        ListActivity.class);
                startActivity(myIntent);
            }
        });
//        setupToolbar();
    }
}
