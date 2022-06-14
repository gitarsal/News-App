package com.assignment.synaos.utils

import com.assignment.synaos.R
import retrofit2.HttpException

object ExceptionParser {

    fun getMessage(exception: Exception): Int {
        return when (exception) {
            is HttpException -> getHttpErrorMessage(exception)
            else -> generalError()
        }
    }

    private fun getHttpErrorMessage(exception: HttpException): Int {
        return when (exception.code()) {
            404 -> R.string.not_found_error
            500->R.string.server_error
            else -> generalError()
        }
    }

    private fun generalError() = R.string.general_error
}