package com.example.ui_unittest_android.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.ui_unittest_android.data.SpendsDatabase
import com.example.ui_unittest_android.data.SpendsTrackerDataSource
import com.example.ui_unittest_android.getOrAwaitValue1
import com.example.ui_unittest_android.ui.spend.SpendViewModel
import com.google.common.truth.Truth
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@Config(manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner::class) //context'i yakalamak için
class SpendViewModelTest : TestCase() {

    private lateinit var spendsDatabase: SpendsDatabase
    private lateinit var viewModel: SpendViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        spendsDatabase = Room.inMemoryDatabaseBuilder(
            context, SpendsDatabase::class.java
        ).allowMainThreadQueries().build()
        val dataSource = SpendsTrackerDataSource(spendsDatabase.getSpendDao())
        viewModel = SpendViewModel(dataSource)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        spendsDatabase.close()
    }

    @Test
    fun testAddingSpend() {
        viewModel.addSpend(100, "Eggs")
        viewModel.getLast20Spends()
        val result = viewModel.last20SpendsLiveData.getOrAwaitValue1().find {
            it.amount == 100 && it.description == "Eggs"
        }
        Truth.assertThat(result != null).isTrue()
    }
}