package com.ar.homenetflixtv.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ViewScoped
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GlideRequestManager

@Module
@InstallIn(ViewComponent::class)
object GlideModule {

    @GlideRequestManager
    @ViewScoped
    @Provides
    fun provideGlideRequestManager(@ActivityContext context: Context): RequestManager {
        return Glide.with(context)
    }

}