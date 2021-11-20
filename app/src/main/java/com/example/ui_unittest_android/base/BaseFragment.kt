package com.example.ui_unittest_android.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ui_unittest_android.data.SpendsDatabase
import com.example.ui_unittest_android.data.SpendsTrackerDataSource
import com.example.ui_unittest_android.ui.spend.SpendViewModel
import com.example.ui_unittest_android.ui.spend.ViewModelFactory

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    lateinit var viewModel: SpendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = SpendsDatabase(requireContext())
        val dataSource = SpendsTrackerDataSource(db.getSpendDao())
        val factory = ViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, factory).get(SpendViewModel::class.java)
    }
}