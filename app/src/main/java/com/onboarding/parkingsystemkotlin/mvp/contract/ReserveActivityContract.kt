package com.onboarding.parkingsystemkotlin.mvp.contract

interface ReserveActivityContract {
    interface ReservePresenter {
        fun onStartDateButtonPress()
        fun onEndDateButtonPress()
        fun onOkButtonPress()
        fun onCancelButtonPress()
    }

    interface ReserveView {
        fun showDateTimePicker(isStartDate: Boolean)
        fun returnToMainActivity()
        fun showReserveSavedToast()
    }
}
