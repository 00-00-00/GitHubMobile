package com.ground0.repository.repository;

import com.ground0.model.Coordinate;
import java.util.List;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by zer0 on 15/10/16.
 */

public interface UserRepository {

  Observable<ResponseBody> pingLocation(Coordinate coordinate);
  Observable<List<String>> getUsers(Double latitude, Double longitude, Long startEpoch, Long endEpoch, Integer radius);
}
