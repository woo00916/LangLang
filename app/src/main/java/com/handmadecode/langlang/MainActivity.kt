package com.handmadecode.langlang
import com.handmadecode.langlang.data.*

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.facebook.stetho.Stetho
import com.handmadecode.langlang.adapter.ListItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AsyncResponse {

    //language setting
    val LANG = "ko"
    val LANGS = arrayListOf<String>("en", "ja")
    var reqId: Int = 0

    var txt_before: String = "" // string to be translated
    val result_list = arrayListOf<TransResultItem>() //list which will save result
    val temp_list = arrayListOf<TransResultItem>()

    var adapter: ListItemAdapter? = null

    lateinit var  db:HistoryDB
   lateinit var  dao:HistoryDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = applicationContext ?: return
        db= HistoryDB.getInstance(applicationContext)
        dao =db?.historydao()
        //views
        val trans_et = findViewById<EditText>(R.id.et_txt) //edit text obj
        //result list
        adapter = ListItemAdapter(this, result_list)
        val listview = findViewById<ListView>(R.id.lv_result)
        listview.setAdapter(adapter)
        Stetho.initializeWithDefaults(this)

        //when the btn clicked
        btn_tr.setOnClickListener {
            val cur_txt = trans_et.text.toString()// fetch string
            if (cur_txt != txt_before && cur_txt.isNotEmpty() && checkDeviceNetworking()) {//not same with before & not empty?& connected&&apiconn.con!=null
                temp_list.add(TransResultItem(cur_txt, arrayListOf(), reqId++))
                for (lang in LANGS) {
                    TranslationThread(this).execute(cur_txt, LANG, lang, reqId.toString())
                }
                txt_before = cur_txt
            }
        }
    }

    override fun processFinish(output: Response) {
        var item: TransResultItem? = temp_list.find { reqId == output.reqId }
        item!!.langs.add(output.result)
        if (item!!.langs.size == LANGS.size) {
            result_list.add(0,item)
            temp_list.remove(item)
            adapter!!.notifyDataSetChanged()
            InsertHistory(dao).execute(Result.create(item.origin_text,item._langs))
        }

    }

    private class InsertHistory(_dao:HistoryDAO) : AsyncTask<Result, Any, Any>(){
        val dao=_dao
        override fun doInBackground(vararg params: Result): Any? {
            dao.insertAll(params[0])
            return null
        }

    }


    private fun checkDeviceNetworking(): Boolean {
        val connMgr: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.getActiveNetworkInfo()
        if (networkInfo == null || !networkInfo.isConnected) {
            Toast.makeText(this, "네트워크 상태를 확인해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }



}

