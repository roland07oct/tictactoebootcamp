package com.company.tictactoebootcamp.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.tictactoebootcamp.utils.BuildConfigFieldsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val buildConfigFieldsProvider: BuildConfigFieldsProvider,
) : ViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> get() = _data

    fun updateVersion() {
        val currentVersion = buildConfigFieldsProvider.get().versionCode.toString()
        _data.value = currentVersion
    }
}