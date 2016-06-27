package com.risencolab.rogernkosi.risenconnect;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by empirestate on 6/21/16.
 */
public interface API {

    @FormUrlEncoded
    @POST("/index.php")
    void getMembers(@FieldMap Map<String, String> map, Callback<String> members);

}
