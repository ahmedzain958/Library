package com.zainco.library.dagger.mitch.di.auth

import javax.inject.Scope

/*
 * to work with ViewModelFactory u need this scope class
 * just don't care about the written copy/past it
 * this is a solution for dagger/ViewModel problem
 * */
@Scope
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AuthScope