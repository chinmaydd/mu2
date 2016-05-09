package chinmaydd.mu2.ui.quote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import chinmaydd.mu2.R;
import chinmaydd.mu2.dummy.DummyContent;

public class EntryActivity extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        DummyContent.ITEMS.clear();

        String url = "http://mu2.herokuapp.com/posts/";

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
                                DummyContent.ITEMS.add(new DummyContent.DummyItem(Integer.toString(i), R.drawable.p1, json_data.get("title").getAsString(), user_data.get("name").getAsString(), json_data.get("description").getAsString(), json_data.get("location").getAsString()));
                            }

                            // launch a different activity
                            Intent launchIntent = new Intent();
                            Class<?> launchActivity;
                            SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);

                            if(settings.getString("email", "none").equals("none")) {
                                // Set global variables
                                launchActivity = LoginActivity.class;


                            } else {
                                Global.UserName = settings.getString("name", "TestUser");
                                Global.Email = settings.getString("email", "TestUser@gmail.com");

                                launchActivity = ListActivity.class;
                            }

                            launchIntent.setClass(getApplicationContext(), launchActivity);
                            startActivity(launchIntent);

                            finish();
                        } catch (Exception err) {
                            Log.i("Error", err.toString());
                        }
                    }
                });
        super.onCreate(savedInstanceState);
    }

}