package com.handmadecode.langlang

import android.os.AsyncTask
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray


class TranslationThread(val context: MainActivity, val adapter: ArrayAdapter<String>) : AsyncTask<String, String, String>() {
    override fun onPreExecute() {
        super.onPreExecute()//프로그래스 바같은거 추가
    }

    override fun doInBackground(vararg params: String): String {
        val txt = params[0]
        val from = params[1]
        val to = params[2]

        return PapagoAPIManager().request(txt,from,to)
    }


    override fun onPostExecute(result: String) {
        super.onPostExecute(result)

        //result list
        context.result_list.add(result)
        adapter.setNotifyOnChange(true)
        adapter.notifyDataSetChanged()

    }

}
