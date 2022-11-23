package com.murat.taskmanager.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.murat.taskmanager.data.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}