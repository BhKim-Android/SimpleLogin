package com.kimbh.auth_facebook.di

import android.app.Activity
import androidx.activity.ComponentActivity
import com.kimbh.auth_facebook.client.FacebookLoginProcessor
import com.kimbh.data.client.LoginClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object FacebookLoginProcessorModule {

    @Provides
    @ActivityScoped
    fun provideFacebookLoginProcessor(
        activity: Activity
    ): LoginClient {
        val componentActivity = activity as? ComponentActivity
            ?: throw IllegalStateException("This processor requires a ComponentActivity")

        return FacebookLoginProcessor(componentActivity)
    }
}