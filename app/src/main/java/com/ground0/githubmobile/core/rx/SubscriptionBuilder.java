package com.ground0.githubmobile.core.rx;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Subscriber;

/**
 * Created by zer0 on 15/10/16.
 */

@Singleton
public class SubscriptionBuilder implements ISubscriptionBuilder{
  @Inject Context context;
  @Inject
  public SubscriptionBuilder(){

  }
  public Builder  builder(){
    return new Builder();
  }

  public class Builder{
    private RestApiSubscription subscription = new RestApiSubscription();

    public Builder onError(OnError onError){
      subscription.onError = onError;
      return this;
    }
    public Builder onNext(OnNext<?> onNext){
      subscription.onNext = onNext;
      return this;
    }

    public Builder setFinishOnComplete(){
      subscription.finishOnComplete = true;
      return this;
    }

    public  RestApiSubscription build(){
      return (RestApiSubscription) subscription;
    }


  }

  private abstract class DefaultRestApiSubscription<T> extends Subscriber<T> {
    protected boolean finishOnComplete;
    @Override public void onCompleted() {
      if(finishOnComplete){
        unsubscribe();
      }
    }
    @Override public void onError(Throwable e) {
      Log.d("DefaultSubscription", "Exception in subscription", e);
      if(e instanceof java.net.SocketException)
        Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
    }
  }

  private class RestApiSubscription extends DefaultRestApiSubscription{
    private OnError onError;
    private OnNext onNext;
    protected RestApiSubscription(){

    }
    public RestApiSubscription(OnError onError, OnNext onNext){
      this.onError = onError;
      this.onNext = onNext;
    }

    @Override public void onError(Throwable e) {
      super.onError(e);
      if(onError != null)
        onError.onError(e);
    }
    @Override public void onNext(Object o) {
      if(onNext != null)
        onNext.onNext(o);
    }
  }



}