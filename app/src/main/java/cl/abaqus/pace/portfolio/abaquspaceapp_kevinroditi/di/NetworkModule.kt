package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.di

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.api.PaceApi
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.interceptor.AuthInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {

        //Fail-fast at app startup
        check(BuildConfig.API_TOKEN.isNotBlank()){
            """API_TOKEN is missing from local.properties."""
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.HEADERS
                }
            )
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

            //Certificate pinning ready (disabled)
            /*
            val certificatePinner = CertificatePinner.Builder()
                .add("api.test.pace.abaqus.cl",
                "sha256/XXXX")
                .build()
             */
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.test.pace.abaqus.cl/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun providesPortfolioApi(retrofit: Retrofit): PaceApi = retrofit.create(PaceApi::class.java)
}
