package com.risencolab.rogernkosi.risenconnect;

import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by empirestate on 6/21/16.
 */
public class RestApiManager {

    private API api;
    public API getAPI(){
        if (api == null){
            GsonBuilder gsonBuilder  = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(String.class, new StringDesirializer());

            api = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gsonBuilder.create()))
                    .build()
                    .create(API.class);
        }
        return api;
    }
}
