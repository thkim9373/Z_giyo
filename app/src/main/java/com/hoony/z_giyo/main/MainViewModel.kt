package com.hoony.z_giyo.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hoony.z_giyo.db.AppRepository
import com.hoony.z_giyo.db.entity.Item
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository by lazy {
        AppRepository.getInstance(application)
    }

    val itemLiveData: LiveData<List<Item>> by lazy {
        repository.getAllItems()
    }

    fun getItem(position: Int): Item {
        return itemLiveData.value?.get(position) as Item
    }

    fun insertItem(item: Item) {
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    fun deleteItem(position: Int) {
        viewModelScope.launch {
            repository.deleteItem(itemLiveData.value?.get(position) as Item)
        }
    }
}