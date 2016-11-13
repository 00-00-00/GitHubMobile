package com.ground0.repository.store;

import com.ground0.model.Repo;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zer0 on 15/10/16.
 */

public interface Store {
  @GET("/users/{username}/repos")
  public Observable<Response<List<Repo>>> getUsers(@Path("username") String userName);
}
