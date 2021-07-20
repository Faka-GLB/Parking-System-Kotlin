package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.onboarding.parkingsystemkotlin.listener.OnInputListener
import com.onboarding.parkingsystemkotlin.mvp.contract.ConfigureParkingLotsContract

class ConfigureParkingPresenter(
    private val view: ConfigureParkingLotsContract.ConfigureParkingView,
    private val inputListener: OnInputListener
) : ConfigureParkingLotsContract.ConfigureParkingPresenter {

    override fun onOkButtonPress() {
        val lots: String = view.getLots()
        if (lots.isNotEmpty()) {
            view.onLotsNotEmpty(lots.toInt(), inputListener)
        } else {
            view.showEmptyInputToast()
        }
    }

    override fun onCancelButtonPress() {
        view.closeDialog()
    }
}
