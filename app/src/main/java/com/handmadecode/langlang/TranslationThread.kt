package com.handmadecode.langlang

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.handmadecode.langlang.data.Response
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray


    interface AsyncResponse {
        fun processFinish(output: Response)
    }
class TranslationThread(_delegate:AsyncResponse) : AsyncTask<String, String, Response>() {
    val delegate=_delegate

    override fun onPreExecute() {
        super.onPreExecute()//프로그래스 바같은거 추가
    }


    override fun doInBackground(vararg params: String): Response {
        val txt = params[0]
        val from = params[1]
        val to = params[2]
        val reqId=params[3].toInt()

        return Response(reqId,PapagoAPIManager().request(txt,from,to))
    }

    override fun onPostExecute(result: Response) {

//        super.onPostExecute(result)
        delegate.processFinish(result);
//
//        //result list
//        context.result_list.add(result)
//        adapter.setNotifyOnChange(true)
//        adapter.notifyDataSetChanged()

    }

}
