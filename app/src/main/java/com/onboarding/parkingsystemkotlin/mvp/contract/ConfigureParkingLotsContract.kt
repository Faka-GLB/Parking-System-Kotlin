package com.onboarding.parkingsystemkotlin.mvp.contract

import com.onboarding.parkingsystemkotlin.listener.OnInputListener

interface ConfigureParkingLotsContract {

    interface ConfigureParkingPresenter {
        fun onOkButtonPress()
        fun onCancelButtonPress()
    }

    interface ConfigureParkingView {
        fun getLots(): String
        fun closeDialog()
        fun showEmptyInputToast()
        fun onLotsNotEmpty(lots: Int, inputListener: OnInputListener)
    }
}
