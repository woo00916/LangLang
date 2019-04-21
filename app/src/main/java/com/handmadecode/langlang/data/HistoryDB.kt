package com.handmadecode.langlang.data

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Result::class),version = 1)
abstract class HistoryDB : RoomDatabase() {
    abstract fun historydao():HistoryDAO

    companion object {
        private var db:HistoryDB?=null
        fun getInstance(context: Context):HistoryDB{
            if(db==null){
                synchronized(HistoryDB::class){
                    db = Room.databaseBuilder(context.getApplicationContext(),HistoryDB::class.java,"HistoryDB.db").build()
                }
            }
        return if (db != null) db as HistoryDB else throw NullPointerException("Expression 'db' must not be null")
        }
    }
}