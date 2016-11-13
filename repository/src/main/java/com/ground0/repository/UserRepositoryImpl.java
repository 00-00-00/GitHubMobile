package com.ground0.repository;

import com.ground0.model.Coordinate;
import com.ground0.repository.repository.UserRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by zer0 on 20/10/16.
 */
@Singleton public class UserRepositoryImpl implements UserRepository {

  @Inject public UserRepositoryImpl() {
  }

  @Inject @Named("cloudStore") UserRepository cloudDataStore;

  @Override public Observable<ResponseBody> pingLocation(Coordinate coordinate) {
    return cloudDataStore.pingLocation(coordinate);
  }

  @Override
  public Observable<List<String>> getUsers(Double latitude, Double longitude, Long startEpoch,
      Long endEpoch, Integer radius) {
    return cloudDataStore.getUsers(latitude, longitude, startEpoch, endEpoch, radius);
  }
}
