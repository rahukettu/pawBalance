package com.pawBalance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HealthViewModel : ViewModel() {
    private val _weight = MutableLiveData<Float>(0f)
    val weight: LiveData<Float> = _weight

    private val _vaccinationStatus = MutableLiveData<String>("Not vaccinated")
    val vaccinationStatus: LiveData<String> = _vaccinationStatus

    private val _checkUpRecords = MutableLiveData<List<String>>(emptyList())
    val checkUpRecords: LiveData<List<String>> = _checkUpRecords

    fun updateWeight(newWeight: Float) {
        _weight.value = newWeight
    }

    fun updateVaccinationStatus(status: String) {
        _vaccinationStatus.value = status
    }

    fun addCheckUpRecord(record: String) {
        val currentRecords = _checkUpRecords.value?.toMutableList() ?: mutableListOf()
        currentRecords.add(record)
        _checkUpRecords.value = currentRecords
    }
}
