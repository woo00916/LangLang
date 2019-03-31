package com.handmadecode.langlang

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.widget.*
import com.handmadecode.langlang.adapter.ListItemAdapter
import com.handmadecode.langlang.data.Languages
import com.handmadecode.langlang.data.Response
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,AsyncResponse{

    //language setting
    val LANG = "ko"
    val LANGS = arrayListOf<String>("en", "ja")
    var reqId :Int = 0

    var txt_before: String = "" // string to be translated
    val result_list = arrayListOf<Languages>() //list which will save result
    val temp_list = arrayListOf<Languages>()

    var adapter:ListItemAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //views
        val trans_et = findViewById<EditText>(R.id.et_txt) //edit text obj
        //result list
         adapter = ListItemAdapter(this, result_list)
        val listview = findViewById<ListView>(R.id.lv_result)
        listview.setAdapter(adapter)

//        listview.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->  })

        //when the btn clicked
        btn_tr.setOnClickListener {
            val cur_txt = trans_et.text.toString()// fetch string
            if (cur_txt != txt_before && cur_txt.isNotEmpty() && checkDeviceNetworking()) {//not same with before & not empty?& connected&&apiconn.con!=null
                temp_list.add(Languages(cur_txt, arrayListOf(),reqId++))
                for (lang in LANGS) {
                   TranslationThread(this).execute(cur_txt, LANG, lang,reqId.toString())
                }
                txt_before = cur_txt
            }
        }

    }
    override fun processFinish(output: Response) {
        var item:Languages? = temp_list.find{reqId==output.reqId}
        item!!.langs.add(output.result)
        if(item!!.langs.size==LANGS.size){
            result_list.add(item)
            temp_list.remove(item)
            adapter!!.notifyDataSetChanged()
        }

    }


    private  fun checkDeviceNetworking():Boolean{
        val connMgr :ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo :NetworkInfo? = connMgr.getActiveNetworkInfo()
        if(networkInfo==null || !networkInfo.isConnected) {
            Toast.makeText(this, "네트워크 상태를 확인해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}
