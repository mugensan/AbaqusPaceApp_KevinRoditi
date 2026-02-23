package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.di

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.api.PaceApi
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.internal.SetFactory.builder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectedTimeOut(30, TimeUnit.SECONDS)
            .readTimeOut(30, TimeUnit.SECONDS)

            //Certificat pinning ready (disabled)
            /*
            val certificatePinner = CertificatePinner.Builder()
                .add("api.test.pace.abaqus.cl", "sha256/...")
                .build()

             */
           return builder.build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.test.pace.abaqus.cl/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()))
            .build()

    @Provides
    @Singleton
    fun providesPorfolioApi(retrofit: Retrofit):
            PaceApi =
                retrofit.create(PaceApi::class.java)
}