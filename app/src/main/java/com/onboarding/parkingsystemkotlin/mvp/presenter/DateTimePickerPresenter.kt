package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.onboarding.parkingsystemkotlin.mvp.contract.DateTimePickerContract

class DateTimePickerPresenter(private val view: DateTimePickerContract.DateTimePickerView, private val isStartButton: Boolean) : DateTimePickerContract.DateTimePickerPresenter {
    override fun onOkButtonPress() {
        view.dismissDateTimePicker() //placeholder for next feature
    }

    override fun onCancelButtonPress() {
        view.dismissDateTimePicker()
    }
}
