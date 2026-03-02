package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.utils

interface AppLogger {
    fun d(message: String)
    fun e(message: String, throwable: Throwable? = null)
}
