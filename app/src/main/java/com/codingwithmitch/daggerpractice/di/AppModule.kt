package com.codingwithmitch.daggerpractice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.codingwithmitch.daggerpractice.R
import com.codingwithmitch.daggerpractice.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideRetrofitInstance(retrofitBuilder: Retrofit.Builder) : Retrofit {
        return retrofitBuilder.build()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(
            R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideAppDrawable(application: Application) : Drawable {
        return ContextCompat.getDrawable(application, R.drawable.logo)!!
    }
}