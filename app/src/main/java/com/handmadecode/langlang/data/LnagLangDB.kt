package com.handmadecode.langlang.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = arrayOf(History::class),version = 1)
@TypeConverters(Converter::class)   // add here
abstract class LnagLangDB : RoomDatabase() {
    abstract fun historydao():HistoryDAO

    companion object {
        private var db:LnagLangDB?=null
        fun getInstance(context: Context):LnagLangDB{
            if(db==null){
                synchronized(LnagLangDB::class){
                    db = Room.databaseBuilder(context.getApplicationContext(),LnagLangDB::class.java,"LnagLangDB.db").build()
                }
            }
        return if (db != null) db as LnagLangDB else throw NullPointerException("Expression 'db' must not be null")
        }
    }
}