package com.example.unit_ui_testexample.ui.addspend

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.ui_unittest_android.R
import com.example.ui_unittest_android.base.BaseFragment
import com.example.ui_unittest_android.databinding.FragmentAddSpendBinding
import com.example.ui_unittest_android.utils.Validator
import com.example.ui_unittest_android.utils.enabled

class AddSpendFragment : BaseFragment(R.layout.fragment_add_spend) {

    private lateinit var binding: FragmentAddSpendBinding
    private var amount: Int = 0
    private var description: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddSpendBinding.bind(view)

        binding.editTextAmount.addTextChangedListener {
            amount = it.toString().trim().toIntOrNull() ?: 0
            binding.buttonAdd.enabled(Validator.validateInput(amount, description))
        }

        binding.editTextDescription.addTextChangedListener {
            description = it?.toString()?.trim() ?: ""
            binding.buttonAdd.enabled(Validator.validateInput(amount, description))
        }

        binding.buttonAdd.setOnClickListener {
            addSpend()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addSpend() {
        viewModel.addSpend(amount, description)
        findNavController().navigateUp()
    }

}