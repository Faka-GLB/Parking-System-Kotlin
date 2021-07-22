package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.onboarding.parkingsystemkotlin.listener.DateTimeListener
import com.onboarding.parkingsystemkotlin.mvp.contract.DateTimePickerContract

class DateTimePickerPresenter(private val view: DateTimePickerContract.DateTimePickerView, private val isStartButton: Boolean) :
    DateTimePickerContract.DateTimePickerPresenter {

    override fun onOkButtonPress(listener: DateTimeListener) {
        if (isStartButton) {
            view.sendStartDateTime(listener)
        } else {
            view.sendEndDateTime(listener)
        }
        view.dismissDateTimePicker()
    }

    override fun onCancelButtonPress() {
        view.dismissDateTimePicker()
    }
}
