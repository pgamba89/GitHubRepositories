package com.example.githubrepositories.di

import android.content.Context
import com.example.githubrepositories.api.ApiService
import com.example.githubrepositories.db.RepoDao
import com.example.githubrepositories.db.RepoDatabase
import com.example.githubrepositories.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): RepoDatabase {
        return RepoDatabase.getInstance(context)
    }

    @Provides
    fun provideRepoDao(appDatabase: RepoDatabase): RepoDao {
        return appDatabase.reposDao()
    }

}