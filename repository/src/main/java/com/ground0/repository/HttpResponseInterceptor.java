package com.ground0.repository;

import android.util.Log;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by zer0 on 12/11/16.
 */

public class HttpResponseInterceptor implements Interceptor {
  @Override public Response intercept(Chain chain) throws IOException {
    String url = chain.request().url().toString();

    try {
      Log.d("HttpRequest",
          "Request uri: "
              + chain.request().url().toString()
              + ", Method:"
              + chain.request()
              .method()
              + ", Cookie: "
              + chain.request().header("cookie")
              + ", Content-type: "
              + chain.request().header("Content-Type"));

      Response response = chain.proceed(chain.request());
      Log.d("HttpResponse", "Response uri: "
          + chain.request().url().toString()
          + ", Status: "
          + response.code()
          + ", Message: "
          + response.message());

      return response;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
