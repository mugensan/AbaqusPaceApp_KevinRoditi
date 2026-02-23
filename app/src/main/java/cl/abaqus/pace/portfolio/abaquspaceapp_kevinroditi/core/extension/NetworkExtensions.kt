package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.extension

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainError
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import retrofit2.Response


/**
 * Converts Retrofit Response to DomainResult for consistent 
 * error handling
 */
suspend fun <T> Response<T>.toDomainResult(): DomainResult<T> {
    return try {
        if (this.isSuccessful) {
            val body = this.body()
            if (body != null) {
                DomainResult.Success(body)
            } else {
                DomainResult.Failure(
                    DomainError.Unknown(
                        Throwable("Response body is null")
                    )
                )
            }
        } else if (this.code() == 401) {
            DomainResult.Failure(DomainError.UnauthorizedError)
        } else {
            DomainResult.Failure(DomainError.NetworkError)
        }
    } catch (e: Exception) {
        DomainResult.Failure(DomainError.Unknown(e))
    }
    
}
