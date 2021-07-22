package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.onboarding.parkingsystemkotlin.mvp.contract.MainActivityContract

class ParkingPresenter(private val model: MainActivityContract.MainActivityModel, private val view: MainActivityContract.MainActivityView) :
    MainActivityContract.MainActivityPresenter {

    override fun onSetParkingButtonPressed() {
        view.showConfigureParkingLotsDialogFragment()
    }

    override fun setParkingLots(lots: Int) {
        model.setParkingLots(lots)
    }

    override fun onNewReservationButtonPressed() {
        view.showNewReservationActivity()
    }
}
