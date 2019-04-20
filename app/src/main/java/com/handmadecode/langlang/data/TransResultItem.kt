package com.handmadecode.langlang.data

data class TransResultItem(var origin: String, var _langs: ArrayList<String>, var _reqId: Int) {
    var langs: ArrayList<String> = _langs
    var reqId: Int = _reqId
    var origin_text: String = origin

}