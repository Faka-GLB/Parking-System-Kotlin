package com.onboarding.parkingsystemkotlin.mvp.contract

import com.onboarding.parkingsystemkotlin.listener.DateTimeListener

interface DateTimePickerContract {
    interface DateTimePickerPresenter {
        fun onOkButtonPress(listener: DateTimeListener)
        fun onCancelButtonPress()
    }

    interface DateTimePickerView {
        fun dismissDateTimePicker()
        fun sendStartDateTime(listener: DateTimeListener)
        fun sendEndDateTime(listener: DateTimeListener)
    }
}
