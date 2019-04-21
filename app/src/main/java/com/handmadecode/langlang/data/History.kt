package com.handmadecode.langlang.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
class History{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="reqId")
    @NonNull
    var historyId:Int=0

    @ColumnInfo(name="ori")
    @NonNull
    lateinit var ori: String

    @ColumnInfo(name="result_1")
    @NonNull
    var result_1: String? =null

    @ColumnInfo(name="result_2")
    var result_2: String?=null

    @ColumnInfo(name="result_3")
    var result_3: String?=null

    @ColumnInfo(name="result_4")
    var result_4: String?=null

    @ColumnInfo(name="result_5")
    var result_5: String?=null

    companion object {
        fun create(_ori:String,  _langs: ArrayList<String>)  : History{
            val history = History()
            history.ori = _ori

            history.result_1=_langs[0]
            history.result_2=_langs[1]
            if(_langs.size>=3) history.result_3=_langs[2]
            if(_langs.size>=4) history.result_4=_langs[3]
            if(_langs.size>=5) history.result_5=_langs[4]

            return history
        }
    }
}