package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.interceptor

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Senior-level Auth Interceptor handling multiple auth schemes and user association.
 */
class AuthInterceptor @Inject constructor() : Interceptor {

    private val userEmail = "demo@pace.financial"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Senior practice: Always log outgoing requests in debug for easier troubleshooting
        Timber.d("Intercepting request: ${originalRequest.url}")

        // Attempt 1: Using "Token" prefix + User Association Header
        val tokenRequest = originalRequest.newBuilder()
            .header("Authorization", "Token ${BuildConfig.API_TOKEN}")
            .header("X-User-Email", userEmail) // Handling User Association professionally
            .build()

        var response = chain.proceed(tokenRequest)

        // if Unauthorized, retry with Bearer scheme
        if (response.code == 401) {
            Timber.w("401 Unauthorized with 'Token' scheme. Retrying with 'Bearer'...")
            response.close()

            val bearerRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
                .header("X-User-Email", userEmail)
                .build()

            response = chain.proceed(bearerRequest)
        }

        if (response.code == 401) {
            Timber.e("Authorization failed for user: $userEmail. Please verify API_TOKEN and user association.")
        }

        return response
    }
}
