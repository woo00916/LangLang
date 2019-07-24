package com.handmadecode.langlang

import android.content.res.Resources
import androidx.core.content.res.TypedArrayUtils.getText
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class PapagoAPIManager {
    private val clientId =Resources.getSystem().getString(R.string.api_key)//애플리케이션 클라이언트 아이디값";
    private val clientSecret =Resources.getSystem().getString(R.string.api_pw)//애플리케이션 클라이언트 시크릿값";
    private val apiURL  = Resources.getSystem().getString(R.string.api_url)

    public fun request(txt:String,from:String,to:String) : String{

        val response = StringBuffer()
        var result =""
        val text = URLEncoder.encode(txt, "UTF-8")

        try {
            // post request
            val url = URL(this.apiURL)
            val con: HttpURLConnection? =  url.openConnection() as HttpURLConnection

            con!!.requestMethod = "POST"
            con!!.setRequestProperty("X-Naver-Client-Id", this.clientId)
            con!!.setRequestProperty("X-Naver-Client-Secret", this.clientSecret)
            con!!.doOutput = true
            val postParams = "source=$from&target=$to&text=$text"

            val wr = DataOutputStream(con!!.outputStream)
            wr.writeBytes(postParams)
            wr.flush()
            wr.close()

            val responseCode = con!!.responseCode

            val br: BufferedReader

            if (responseCode == 200) { // 정상 호출
                br = BufferedReader(InputStreamReader(con!!.inputStream))
                var inputLine: String?
                do {
                    inputLine = br.readLine()
                    if (inputLine == null) break
                    response.append(inputLine);

                } while (true)
                result = response.toString().parseResultJson()
            } else {  // 에러 발생
                br = BufferedReader(InputStreamReader(con!!.errorStream))
                //실패했을 경우 빈 문자열이라도 담을까 아니면
                result = "$to 번역에 실패햇습니다"
            }
            br.close()
        } catch (e: Exception) {
            println(e)
        }
        return result
    }
    private fun String.parseResultJson():String{
        val jsonObj = JSONObject(this)
        val message : JSONObject = (jsonObj.get("message") as JSONObject).get("result") as JSONObject
        return message.get("translatedText").toString()

    }

}