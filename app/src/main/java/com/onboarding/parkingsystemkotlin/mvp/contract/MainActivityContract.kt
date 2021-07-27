package com.onboarding.parkingsystemkotlin.mvp.contract

interface MainActivityContract {

    interface MainActivityPresenter {
        fun onSetParkingButtonPressed()
        fun setParkingLots(lots: Int)
        fun onNewReservationButtonPressed()
        fun onRemoveOldReservationsButtonPressed()
    }

    interface MainActivityModel {
        fun setParkingLots(lots: Int)
        fun getParkingLots(): Int
        fun removeOldReservations(): Int
    }

    interface MainActivityView {
        fun showConfigureParkingLotsDialogFragment()
        fun showNewReservationActivity()
        fun showReservationsRemovedToast()
    }
}
