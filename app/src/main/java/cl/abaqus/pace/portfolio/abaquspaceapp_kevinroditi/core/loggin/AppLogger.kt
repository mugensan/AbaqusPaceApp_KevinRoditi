package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.loggin

interface AppLogger {
    fun d(message: String)
    fun e(message: String, throwable: Throwable? = null)
}