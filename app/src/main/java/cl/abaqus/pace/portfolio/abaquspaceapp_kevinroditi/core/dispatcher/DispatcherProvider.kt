package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Abstraction of coroutine dispatchers
 * Allows testing and consistent use of Dispatcher.IO
 */

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}