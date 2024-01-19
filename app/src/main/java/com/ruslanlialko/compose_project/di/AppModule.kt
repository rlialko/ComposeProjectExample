package com.ruslanlialko.compose_project.di

import com.ruslanlialko.compose_project.BuildConfig
import com.ruslanlialko.compose_project.data.remote.ExampleApi
import com.ruslanlialko.compose_project.data.repository.ExampleRepositoryImpl
import com.ruslanlialko.compose_project.domain.repository.ExampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExampleApi(): ExampleApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        // TODO change base url and move it some different place
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExampleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExampleRepository(exampleApi: ExampleApi): ExampleRepository {
        return ExampleRepositoryImpl(exampleApi)
    }


}