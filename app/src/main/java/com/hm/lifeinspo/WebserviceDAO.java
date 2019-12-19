package com.hm.lifeinspo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Makes calls to server to get quotes data.
 *
 */
public class WebserviceDAO {

    // TODO: Eventually move to config file
    private final static String SERVER_URL = "http://dailyaphorisms.hazella.co/api.php";

    private RequestQueue queue;

    public WebserviceDAO(Context context) {
        // Instantiate the RequestQueue
        queue = Volley.newRequestQueue(context);
    }

    /**
     * Get list of quotes from server.
     * @param callback
     */
    public void get(final VolleyCallback callback) {
        String url = SERVER_URL + "?action=get_app_list";
        // http://dailyaphorisms.hazella.co/api.php?action=get_app_list

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //System.out.println("Response is: " + response);
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
            @Override
                    public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
                });
        // Add the request to the RequestQueue
        queue.add(stringRequest);
    }


}
