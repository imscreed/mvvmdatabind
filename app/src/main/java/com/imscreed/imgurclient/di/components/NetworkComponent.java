package com.imscreed.imgurclient.di.components;

import com.imscreed.imgurclient.di.modules.NetworkModule;
import com.imscreed.imgurclient.services.ImgurService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ibrahim on 2018-02-18.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    ImgurService provideImgurService();
}
