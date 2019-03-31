package com.handmadecode.langlang.data

data class Response(val _reqId: Int, val _result: String) {
    val reqId: Int = _reqId
    var result: String = _result
}