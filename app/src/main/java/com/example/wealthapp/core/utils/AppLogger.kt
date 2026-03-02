package com.example.wealthapp.core.utils

interface AppLogger {
    fun d(message: String)
    fun e(message: String, throwable: Throwable? = null)
}
