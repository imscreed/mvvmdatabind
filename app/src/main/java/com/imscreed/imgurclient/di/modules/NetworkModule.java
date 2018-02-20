package com.imscreed.imgurclient.di.modules;

import com.imscreed.imgurclient.services.ImgurService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ibrahim on 2018-02-18.
 */

@Module
public class NetworkModule {
    private static final String BASE_URL = "https://api.imgur.com/3/";

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    ImgurService provideImgurService(Retrofit retrofit){
        return retrofit.create(ImgurService.class);
    }
}
