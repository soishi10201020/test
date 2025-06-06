package com.example.dxfviewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DxfViewModel : ViewModel() {
    private val repository = DxfRepository()

    private val _entities = MutableLiveData<List<LineEntity>>()
    val entities: LiveData<List<LineEntity>> get() = _entities

    fun loadDxfFile(path: String) {
        _entities.value = repository.loadDxf(path)
    }
}
