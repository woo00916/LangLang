package com.handmadecode.langlang.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface HistoryDAO {
    @Insert
    fun insertAll(vararg history:Result)

}