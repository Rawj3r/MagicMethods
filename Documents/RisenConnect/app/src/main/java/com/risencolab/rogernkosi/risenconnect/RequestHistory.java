package com.risencolab.rogernkosi.risenconnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by empirestate on 6/23/16.
 */
public class RequestHistory extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String username = "stephan";

    private List<RequestModel> requests = new ArrayList<>();
    private List<String> keys = new ArrayList<>();
    private List<String> inputs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_history);

        populateTimeRequests(username);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateTimeRequests(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.time_requests) {
//            populateTimeRequests(username);
//            return true;
//        } else if (id == R.id.members_inbuilding) {
//            Intent i = new Intent(RequestHistory.this, MainActivity.class);
//            startActivity(i);
//            return true;
//        } else if (id == R.id.change_available) {
//            Intent i = new Intent(RequestHistory.this, MainActivity.class);
//            startActivity(i);
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private void populateTimeRequests(String username) {

        keys.add("method");
        keys.add("receiverUsername");

        inputs.add("retrieve_request");
        inputs.add(username);

        Log.d("keys", keys.toString());
        Log.d("inputs", inputs.toString());

        new PostAsyncRequests().execute(keys, inputs);
    }

    private class PostAsyncRequests extends AsyncTask<List<String>, Void, JSONArray> {
        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://api.nkosiroger.co.za/rnc/index.php";


        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(RequestHistory.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(List<String>... args) {

            try {

                HashMap<String, String> params = new HashMap<>();

                for (int i = 0; i < args[0].size(); i++)
                    params.put(args[0].get(i), args[1].get(i));

                Log.d("request", "starting");

                JSONArray json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(JSONArray json) {


            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                if(requests!=null) {
                    requests.clear();
                }
                for (int i = 1; i < json.length(); i++) {

                    try {


                        RequestModel request = new RequestModel(json.getJSONObject(i).getString("requestTimeID"),
                                json.getJSONObject(i).getString("memRID"),
                                json.getJSONObject(i).getString("memSID"),
                                json.getJSONObject(i).getString("senderUsername"),
                                json.getJSONObject(i).getString("requestStatus"),
                                json.getJSONObject(i).getString("requestContent"));
                        requests.add(request);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                mRecyclerView = (RecyclerView) findViewById(R.id.request_recycler_view);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);

                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(RequestHistory.this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                // specify an adapter (see also next example)
                mAdapter = new MyRequestsAdapter(RequestHistory.this, requests);

                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }
}