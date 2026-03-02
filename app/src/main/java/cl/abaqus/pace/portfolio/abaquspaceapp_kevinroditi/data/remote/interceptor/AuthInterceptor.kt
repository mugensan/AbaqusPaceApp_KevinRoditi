package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.interceptor

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Attempt 1: Using "Token" prefix
        Timber.d("Attempting request with Token header...")
        val tokenRequest = originalRequest.newBuilder()
            .header("Authorization", "Token ${BuildConfig.API_TOKEN}")
            .build()

        val tokenResponse = chain.proceed(tokenRequest)

        if (tokenResponse.code != 401) {
            Timber.d("Request successful with Token header (Code: ${tokenResponse.code})")
            return tokenResponse
        }

        Timber.w("Request failed with 401 using Token header. Retrying with Bearer...")
        tokenResponse.close()

        // Attempt 2: Using "Bearer" prefix
        val bearerRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
            .build()

        val bearerResponse = chain.proceed(bearerRequest)
        
        if (bearerResponse.code == 401) {
            Timber.e("Request failed with 401 using both Token and Bearer headers.")
        } else {
            Timber.d("Request successful with Bearer header (Code: ${bearerResponse.code})")
        }

        return bearerResponse
    }
}
