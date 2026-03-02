package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.utils

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimberLogger @Inject constructor() : AppLogger {
    override fun d(message: String) {
        Timber.d(message)
    }

    override fun e(message: String, throwable: Throwable?) {
        Timber.e(throwable, message)
    }
}
