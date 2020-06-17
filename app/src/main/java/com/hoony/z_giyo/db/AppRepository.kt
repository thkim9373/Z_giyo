package com.hoony.z_giyo.db

import android.app.Application
import androidx.lifecycle.LiveData
import com.hoony.z_giyo.db.entity.Item
import com.hoony.z_giyo.db.entity.ItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository {

    companion object {
        private var instance: AppRepository? = null

        fun getInstance(application: Application): AppRepository {
            if (instance == null) {
                instance = createAppRepository(application)
            }
            return instance as AppRepository
        }

        private fun createAppRepository(application: Application): AppRepository {
            val appRepository = AppRepository()
            appRepository.let {
                val appDataBase = AppDatabase.getInstance(application)
                it.itemDao = appDataBase.itemDao
            }
            return appRepository
        }
    }

    private lateinit var itemDao: ItemDao

    fun getAllItems(): LiveData<List<Item>> {
        return itemDao.getAll()
    }

    suspend fun insertItem(item: Item) {
        withContext(Dispatchers.IO) {
            itemDao.insert(item)
        }
    }

    suspend fun deleteItem(item: Item) {
        withContext(Dispatchers.IO) {
            itemDao.delete(item)
        }
    }
}