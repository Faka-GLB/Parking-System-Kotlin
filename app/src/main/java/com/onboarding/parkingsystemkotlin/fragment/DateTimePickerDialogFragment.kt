package com.onboarding.parkingsystemkotlin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.onboarding.parkingsystemkotlin.databinding.DateTimePickerReservationFragmentBinding
import com.onboarding.parkingsystemkotlin.listener.DateTimeListener
import com.onboarding.parkingsystemkotlin.mvp.contract.DateTimePickerContract
import com.onboarding.parkingsystemkotlin.mvp.presenter.DateTimePickerPresenter
import com.onboarding.parkingsystemkotlin.mvp.view.DateTimePickerView
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils
import java.lang.ClassCastException

class DateTimePickerDialogFragment : DialogFragment() {
    private lateinit var binding: DateTimePickerReservationFragmentBinding
    private lateinit var presenter: DateTimePickerContract.DateTimePickerPresenter
    private lateinit var dateTimeListener: DateTimeListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = DateTimePickerReservationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { fragmentActivity ->
            try {
                dateTimeListener = fragmentActivity as DateTimeListener
            } catch (e: ClassCastException) {
                Log.e(ConstantUtils.DATE_TIME_PICKER_DIALOG_TAG, e.javaClass.canonicalName + e.message)
            }
            arguments?.let {
                presenter = DateTimePickerPresenter(
                    view = DateTimePickerView(dialogFragment = this, binding),
                    isStartButton = it.getBoolean(START_DATE_FLAG_TAG)
                )
            }
            setListeners()
        }
    }

    private fun setListeners() {
        binding.buttonReservationFragmentOk.setOnClickListener { this.presenter.onOkButtonPress(dateTimeListener) }
        binding.buttonReservationFragmentCancel.setOnClickListener { this.presenter.onCancelButtonPress() }
    }

    companion object {
        private const val START_DATE_FLAG_TAG: String = "START_DATE_FLAG"
        fun newInstance(isStartDate: Boolean): DateTimePickerDialogFragment {
            val args = Bundle()
            args.putBoolean(START_DATE_FLAG_TAG, isStartDate)
            val fragment = DateTimePickerDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
