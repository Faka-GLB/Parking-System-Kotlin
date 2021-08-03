package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.onboarding.parkingsystemkotlin.mvp.contract.ShowReservationsContract

class ShowReservationsPresenter(
    private val view: ShowReservationsContract.ShowReservationsView,
    private val model: ShowReservationsContract.ShowReservationsModel
) : ShowReservationsContract.ShowReservationsPresenter {

    override fun showReservations() {
        view.showReservations(model.listReservations())
    }
}
