package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result

sealed class DomainError {
    object NetworkError : DomainError()
    object UnauthorizedError : DomainError()
    data class Unknown(val throwable: Throwable):
            DomainError()
}