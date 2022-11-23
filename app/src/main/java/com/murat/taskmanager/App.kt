package com.murat.taskmanager

import android.app.Application
import androidx.room.Room
import com.murat.taskmanager.data.local.db.AppDataBase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "task-db"
        ).allowMainThreadQueries().build()
    }

    companion object{
        lateinit var db : AppDataBase
    }
}