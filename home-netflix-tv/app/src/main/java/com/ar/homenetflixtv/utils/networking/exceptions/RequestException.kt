package com.ar.homenetflixtv.utils.networking.exceptions

class RequestException : Throwable {

    var httpStatusCode: Int = 0

    constructor(httpStatusCode: Int) : super() {
        this.httpStatusCode = httpStatusCode
    }

    constructor(throwable: Throwable) : super(throwable)

}
