package com.callsigntask.queries.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    fun getTestHash(): String {
        return hashCode().toString()
    }
}