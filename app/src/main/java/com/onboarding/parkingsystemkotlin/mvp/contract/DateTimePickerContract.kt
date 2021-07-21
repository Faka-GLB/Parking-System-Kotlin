package com.onboarding.parkingsystemkotlin.mvp.contract

interface DateTimePickerContract {
    interface DateTimePickerPresenter {
        fun onOkButtonPress()
        fun onCancelButtonPress()
    }

    interface DateTimePickerView {
        fun dismissDateTimePicker()
    }
}
