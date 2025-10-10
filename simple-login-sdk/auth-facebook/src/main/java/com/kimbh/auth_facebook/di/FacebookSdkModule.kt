//package com.kimbh.auth_facebook.di
//
//import com.facebook.CallbackManager
//import com.facebook.login.LoginManager
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object FacebookSdkModule {
//
//    @Singleton
//    @Provides
//    fun provideCallbackManager(): CallbackManager = CallbackManager.Factory.create()
//
//    @Singleton
//    @Provides
//    fun provideLoginManager(): LoginManager = LoginManager.getInstance()
//}