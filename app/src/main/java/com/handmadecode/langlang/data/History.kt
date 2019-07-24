package com.handmadecode.langlang.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
class History{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="historyId")
    @NonNull
    var historyId:Int=0

    @ColumnInfo(name="ori")
    @NonNull
    lateinit var ori: String

    @ColumnInfo(name="langs_json")
    @NonNull
    lateinit var langs_json: ArrayList<String>

    @ColumnInfo(name="reqId")
    var reqId: Int = 0
    companion object {
        fun create(_reqId:Int, _ori:String,  _langs: ArrayList<String>)  : History{
            val history = History()
            history.reqId=_reqId
            history.ori = _ori
            history.langs_json=_langs
            return history
        }
    }
}