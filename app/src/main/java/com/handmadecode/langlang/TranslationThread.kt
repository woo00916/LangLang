package com.handmadecode.langlang

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.handmadecode.langlang.data.Response

interface AsyncResponse {
    fun processFinish(output: Response)
}

class TranslationThread(_delegate: AsyncResponse) : AsyncTask<String,String,Response>() {
    val delegate = _delegate

    override fun onPreExecute() {
        super.onPreExecute()//프로그래스 바같은거 추가
    }

    override fun doInBackground(vararg params: String): Response {
        val txt = params[0]
        val from = params[1]
        val to = params[2]
        val reqId = params[3].toInt()

        return Response(reqId, PapagoAPIManager().request(txt, from, to))
    }

    //implemented in MainThread
    override fun onPostExecute(result: Response) {
        delegate.processFinish(result);
    }

}
