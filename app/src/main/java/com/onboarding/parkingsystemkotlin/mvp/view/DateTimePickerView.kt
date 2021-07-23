package com.onboarding.parkingsystemkotlin.mvp.view

import androidx.fragment.app.DialogFragment
import com.onboarding.parkingsystemkotlin.databinding.DateTimePickerReservationFragmentBinding
import com.onboarding.parkingsystemkotlin.listener.DateTimeListener
import com.onboarding.parkingsystemkotlin.mvp.contract.DateTimePickerContract
import com.onboarding.parkingsystemkotlin.mvp.view.base.FragmentView
import java.util.Calendar
import java.util.GregorianCalendar

class DateTimePickerView(dialogFragment: DialogFragment, private val binding: DateTimePickerReservationFragmentBinding) :
    FragmentView(dialogFragment),
    DateTimePickerContract.DateTimePickerView {

    override fun dismissDateTimePicker() {
        (getFragment() as DialogFragment).dismiss()
    }

    override fun sendStartDateTime(listener: DateTimeListener) {
        listener.sendStartDateTime(getCalendar())
    }

    override fun sendEndDateTime(listener: DateTimeListener) {
        listener.sendEndDateTime(getCalendar())
    }

    private fun getCalendar(): Calendar {
        val dateTime: Calendar = GregorianCalendar()
        dateTime.set(
            binding.datePikerReservationFragment.year, binding.datePikerReservationFragment.month,
            binding.datePikerReservationFragment.dayOfMonth,
            binding.timePickerReservationFragment.hour,
            binding.timePickerReservationFragment.minute
        )
        return dateTime
    }
}
