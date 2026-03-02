package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.interceptor

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // First Attempt -> token
        val tokenRequest = originalRequest.newBuilder()
            .header("Authorization", "Token ${BuildConfig.API_TOKEN}")
            .build()

        val tokenResponse = chain.proceed(tokenRequest)

        // if Success -> return
        if (tokenResponse.code != 401) {
            return tokenResponse
        }

        // Closing first response before retrying
        tokenResponse.close()

        // Retry once with Bearer
        val bearerRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
            .build()

        return chain.proceed(bearerRequest)
    }
}
