package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result

sealed class DomainError {
    object NetworkError : DomainError() {
        override fun toString() = "Network Error - Please check your connection"
    }
    object UnauthorizedError : DomainError() {
        override fun toString() = "Unauthorized - Check your API Token"
    }
    data class Unknown(val throwable: Throwable) : DomainError() {
        override fun toString() = throwable.localizedMessage ?: "An unknown error occurred"
    }
}
