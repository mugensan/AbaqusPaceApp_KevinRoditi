package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result

sealed class DomainResult<out T> {
    data class Success<out T>(val data: T) : DomainResult<T>()
    data class Failure(val exception: Exception) : DomainResult<Nothing>()
}