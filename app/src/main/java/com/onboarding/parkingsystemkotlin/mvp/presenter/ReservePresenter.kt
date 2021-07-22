package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract

class ReservePresenter (private val view: ReserveActivityContract.ReserveView): ReserveActivityContract.ReservePresenter {
    override fun onStartDateButtonPress() {
        view.showDateTimePicker(true)
    }

    override fun onEndDateButtonPress() {
        view.showDateTimePicker(false)
    }

    override fun onOkButtonPress() {
        view.showReserveSavedToast() //placeholder for next feature
        view.returnToMainActivity()
    }

    override fun onCancelButtonPress() {
        view.returnToMainActivity()
    }
}
