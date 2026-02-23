package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${
                BuildConfig.API_TOKEN
            }")
            .build()
        return chain.proceed(request)

    }
}