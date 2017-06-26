package com.repitch.towerpower.api;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Single;

/**
 * Created by repitch on 25.06.17.
 */
public interface RetrofitInterface {

    @POST("process.php")
    Single<LocationInfo> getLocationInfo(@Body LocationRequest request);

}
