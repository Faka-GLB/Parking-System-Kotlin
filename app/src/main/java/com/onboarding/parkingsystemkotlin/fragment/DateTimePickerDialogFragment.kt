package com.onboarding.parkingsystemkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.onboarding.parkingsystemkotlin.databinding.DateTimePickerReservationFragmentBinding
import com.onboarding.parkingsystemkotlin.mvp.contract.DateTimePickerContract
import com.onboarding.parkingsystemkotlin.mvp.presenter.DateTimePickerPresenter
import com.onboarding.parkingsystemkotlin.mvp.view.DateTimePickerView

class DateTimePickerDialogFragment : DialogFragment() {
    private lateinit var binding: DateTimePickerReservationFragmentBinding
    private lateinit var presenter: DateTimePickerContract.DateTimePickerPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = DateTimePickerReservationFragmentBinding.inflate(layoutInflater)
        arguments?.let {
            presenter = DateTimePickerPresenter(view = DateTimePickerView(dialogFragment = this, binding), it.getBoolean(START_DATE_FLAG_TAG))
        }
        setListeners()
        return binding.root
    }

    private fun setListeners(){
        binding.btnReservationFragmentOk.setOnClickListener { this.presenter.onOkButtonPress() }
        binding.btnReservationFragmentCancel.setOnClickListener { this.presenter.onCancelButtonPress() }
    }

    companion object{
        const val START_DATE_FLAG_TAG: String = "START_DATE_FLAG"
        fun newInstance(isStartDate: Boolean): DateTimePickerDialogFragment{
            val args = Bundle()
            args.putBoolean(START_DATE_FLAG_TAG, isStartDate)
            val fragment = DateTimePickerDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
