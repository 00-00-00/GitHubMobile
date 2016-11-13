package com.ground0.githubmobile.core.di;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zer0 on 9/10/16.
 */


@Scope
@Retention(RUNTIME)
public @interface PerActivity {}