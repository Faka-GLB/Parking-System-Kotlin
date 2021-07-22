package com.onboarding.parkingsystemkotlin.mvp.view

import androidx.fragment.app.DialogFragment
import com.onboarding.parkingsystemkotlin.databinding.DateTimePickerReservationFragmentBinding
import com.onboarding.parkingsystemkotlin.mvp.contract.DateTimePickerContract
import com.onboarding.parkingsystemkotlin.mvp.view.base.FragmentView

class DateTimePickerView(dialogFragment: DialogFragment, binding: DateTimePickerReservationFragmentBinding) : FragmentView(dialogFragment),
    DateTimePickerContract.DateTimePickerView {

    override fun dismissDateTimePicker() {
        (getFragment() as DialogFragment).dismiss()
    }
}
