/*
 *abiola 2024
 */

package com.mshdabiola.skeletonandroid

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.mshdabiola.skeletonandroid.util.ProfileVerifierLogger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class SkeletonApplication : Application(), ImageLoaderFactory {

    @Inject
    lateinit var imageLoader: dagger.Lazy<ImageLoader>

    @Inject
    lateinit var profileVerifierLogger: ProfileVerifierLogger

    override fun onCreate() {
        super.onCreate()

        if (packageName.contains("debug")) {
            Timber.plant(Timber.DebugTree())
            Timber.e("log on app create")
        }
        profileVerifierLogger()
    }

    override fun newImageLoader(): ImageLoader = imageLoader.get()
}
