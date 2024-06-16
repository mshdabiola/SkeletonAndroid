/*
 *abiola 2024
 */

package com.mshdabiola.network.di

import android.content.Context
import android.telecom.Call
import androidx.tracing.trace
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.DebugLogger
import com.mshdabiola.network.BuildConfig
import com.mshdabiola.network.Config
import com.mshdabiola.network.INetworkDataSource
import com.mshdabiola.network.NetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun clientProvider() = HttpClient(Android) {
        install(Resources)
        install(Logging) {
            logger = io.ktor.client.plugins.logging.Logger.SIMPLE
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    this.ignoreUnknownKeys = true
                },
            )
        }
        defaultRequest {
            headers {
                this[HttpHeaders.Authorization] = "Bearer ${Config.token}"
                this[HttpHeaders.Accept] = "application/json"
                this[HttpHeaders.ContentType] = "application/json"
            }
            url {
                host = "api.spotify.com"
                protocol = URLProtocol.HTTPS
            }
        }
        install(UserAgent) {
            agent = "my app"
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(5)
            exponentialDelay()
        }
        install(HttpCache) {
            val file = File.createTempFile("abiola", "tem")
            publicStorage(FileStorage(file))
        }
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): okhttp3.Call.Factory = trace("NiaOkHttpClient") {
        OkHttpClient.Builder()
//            .addInterceptor(
//                HttpLoggingInterceptor()
//                    .apply {
//                        if (BuildConfig.DEBUG) {
//                            setLevel(HttpLoggingInterceptor.Level.BODY)
//                        }
//                    },
//            )
            .build()
    }

    @Provides
    @Singleton
    fun imageLoader(
        // We specifically request dagger.Lazy here, so that it's not instantiated from Dagger.
        okHttpCallFactory: dagger.Lazy<okhttp3.Call.Factory>,
        @ApplicationContext application: Context,
    ): ImageLoader = trace("NiaImageLoader") {
        ImageLoader.Builder(application)
            .callFactory { okHttpCallFactory.get() }
            .components { add(SvgDecoder.Factory()) }
            // Assume most content images are versioned urls
            // but some problematic images are fetching each time
            .respectCacheHeaders(false)
            .apply {
                if (BuildConfig.DEBUG) {
                    logger(DebugLogger())
                }
            }
            .build()
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface NetworkBind {

    @Binds
    @Singleton
    fun bindNetworkDataSource(iNetworkDataSource: INetworkDataSource): NetworkDataSource
}
