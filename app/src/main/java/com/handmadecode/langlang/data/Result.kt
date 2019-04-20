package com.handmadecode.langlang.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "History",primaryKeys = ["reqId","ori"])
class Result{
    @ColumnInfo(name="reqId")
    @NonNull
    lateinit var reqId: String
    @ColumnInfo(name="ori")
    @NonNull
    lateinit var ori: String
    @ColumnInfo(name="result")
    var result: String? =null

    companion object {
        fun create(_reqId:String,_ori:String,_result:String)  : Result{
            val history = Result()
            history.reqId = _reqId
            history.ori = _ori
            return history
        }
    }
}