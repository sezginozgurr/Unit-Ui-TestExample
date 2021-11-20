package com.example.ui_unittest_android.ui.spend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ui_unittest_android.data.SpendsTrackerDataSource

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val dataSource: SpendsTrackerDataSource
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpendViewModel(dataSource) as T
    }
}