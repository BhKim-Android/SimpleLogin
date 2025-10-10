//package com.kimbh.auth_facebook.di
//
//import androidx.activity.ComponentActivity
//import com.facebook.CallbackManager
//import com.facebook.login.LoginManager
//import com.kimbh.auth_facebook.client.FacebookLoginClient
//import com.kimbh.data.client.LoginClient
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityComponent
//import dagger.hilt.android.scopes.ActivityScoped
//import dagger.multibindings.IntoSet
//
//@Module
//@InstallIn(ActivityComponent::class)
//object FacebookLoginClientModule {
//
//    @Provides
//    @ActivityScoped
//    @IntoSet
//    fun provideFacebookLoginClient(
//        activity: ComponentActivity,
//        callbackManager: CallbackManager,
//        loginManager: LoginManager
//    ): LoginClient {
//        return FacebookLoginClient(activity, callbackManager, loginManager)
//    }
//}