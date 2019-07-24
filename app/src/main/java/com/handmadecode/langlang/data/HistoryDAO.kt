package com.handmadecode.langlang.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDAO {
    @Insert
    fun insertAll(vararg history:History)

    @get:Query("select * from History")//paging?
    val all:List<History>
}