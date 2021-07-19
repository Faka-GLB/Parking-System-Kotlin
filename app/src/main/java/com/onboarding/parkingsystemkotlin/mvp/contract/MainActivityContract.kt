package com.onboarding.parkingsystemkotlin.mvp.contract

interface MainActivityContract {

    interface MainActivityPresenter {
        fun onSetParkingButtonPressed()
        fun setParkingLots(lots: Int)
    }

    interface MainActivityModel {
        fun setParkingLots(lots: Int)
        fun getParkingLots(): Int
    }

    interface MainActivityView {
        fun showConfigureParkingLotsDialogFragment()
    }
}
