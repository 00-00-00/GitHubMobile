package com.ground0.repository.store;

import android.util.Log;
import com.ground0.model.Repo;
import com.ground0.repository.BuildConfig;
import com.ground0.repository.CustomObjectMapper;
import com.ground0.repository.HttpResponseInterceptor;
import com.ground0.repository.HttpResponseStatusOperator;
import com.ground0.repository.repository.UserRepository;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by zer0 on 15/10/16.
 */

@Singleton @Named("cloudStore") public class CloudDataStore implements UserRepository {

  static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s
  static final int READ_TIMEOUT_MILLIS = 20 * 1000; // 20
  private String host;

  private CustomObjectMapper objectMapper;
  private Store restImpl;
  HttpResponseStatusOperator responseStatusOperator;

  @Inject public CloudDataStore(CustomObjectMapper customObjectMapper) {
    this.objectMapper = customObjectMapper;
    this.responseStatusOperator = new HttpResponseStatusOperator(objectMapper);
    this.host = BuildConfig.HOST;
    Log.i("CloudDataStore", "host: " + host);
    final OkHttpClient.Builder builder =
        new OkHttpClient.Builder().addInterceptor(new HttpResponseInterceptor())
            .connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

    OkHttpClient client = builder.build();

    Retrofit retrofit = new Retrofit.Builder().client(client)
        .baseUrl(host)
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build();
    restImpl = retrofit.create(Store.class);
  }

  @Override public Observable<List<Repo>> getUsers(String userName) {
    return restImpl.getUsers(userName).lift(responseStatusOperator);
  }
}
