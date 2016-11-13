package com.ground0.repository.store;

import com.ground0.model.Coordinate;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zer0 on 15/10/16.
 */

public interface Store {

  @POST("insert/ping") Observable<Response<ResponseBody>> pingLocation(@Body Coordinate coordinate);

  @GET("get/users/{lon}/{lat}/{startEpoch}/{endEpoch}/{radius}")
  Observable<Response<List<String>>> getUsers(@Path("lat") Double latitude,
      @Path("lon") Double longitude, @Path("startEpoch") Long startEpoch,
      @Path("endEpoch") Long endEpoch, @Path("radius") Integer radius);
}
