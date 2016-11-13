package com.ground0.repository;

import com.ground0.model.Repo;
import com.ground0.repository.repository.UserRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created by zer0 on 20/10/16.
 */
@Singleton public class UserRepositoryImpl implements UserRepository {

  @Inject public UserRepositoryImpl() {
  }

  @Inject @Named("cloudStore") UserRepository cloudDataStore;

  @Override public Observable<List<Repo>> getUsers(String userName) {
    return cloudDataStore.getUsers(userName);
  }
}
