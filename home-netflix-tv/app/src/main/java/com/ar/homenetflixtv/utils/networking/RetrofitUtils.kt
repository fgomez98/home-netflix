package com.ar.homenetflixtv.utils.networking

import com.ar.homenetflixtv.utils.networking.exceptions.NoConnectivityException
import com.ar.homenetflixtv.utils.networking.exceptions.RequestException
import io.reactivex.Single
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

object RetrofitUtils {

    /**
     * Wraps the given {@Link Single} that emits a {@Link Response} and unwraps it's content in case
     * of success, or unwraps the exception in case of error.
     */
    fun <T> performRequest(request: Single<Response<T>>): Single<T> {
        return request.onErrorResumeNext { t -> Single.error(convertException(t)) }
            .map(RetrofitUtils::unwrapResponse)
    }

    /**
     * Wraps exceptions raised by requests into domain-specific exceptions. Call {getCause()} to get
     * Retrofit thrown exception
     */
    private fun convertException(throwable: Throwable): Throwable {
        if (throwable is UnknownHostException) {
            return NoConnectivityException(throwable)
        }
        if (throwable is IOException) {
            throw RequestException(throwable)
        }
        return throwable
    }

    /**
     * Unwraps the given {@Link Response}, returning the body if it's a success response, or
     * throwing {@Link RequestException} if the status code is an error.
     */
    private fun <T> unwrapResponse(response: Response<T>): T? {
        if (isRequestFailed(response)) {
            throw RequestException(response.code())
        }
        return response.body()
    }

    /**
     * Returns whether the request response represents a failure, either due to connectivity error
     * or error status code.
     */
    private fun <T> isRequestFailed(response: Response<T>): Boolean {
        return !response.isSuccessful || isErrorCode(response.code())
    }

    private fun isErrorCode(code: Int): Boolean {
        return code in 400..600
    }
}