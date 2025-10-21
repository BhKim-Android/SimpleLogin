package com.kimbh.auth_google.di

import android.content.Context
import com.kimbh.auth_google.client.GoogleLoginClient
import com.kimbh.data.client.LoginClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object GoogleLoginClientModule {

    @Provides
    @IntoSet
    fun provideGoogleLoginClient(
        @ApplicationContext context: Context
    ): LoginClient {
        return GoogleLoginClient(context)
    }
}