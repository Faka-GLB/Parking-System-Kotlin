package com.onboarding.parkingsystemkotlin.mvp.contract

import com.onboarding.parkingsystemkotlin.entity.Reservation

interface ShowReservationsContract {

    interface ShowReservationsPresenter {
        fun showReservations()
    }

    interface ShowReservationsView {
        fun showReservations(list: List<Reservation>)
    }

    interface ShowReservationsModel {
        fun getReservationStartDateString(reservation: Reservation): String?
        fun getReservationEndDateString(reservation: Reservation): String?
        fun getReservationParkingLotString(reservation: Reservation): String
        fun listReservations(): List<Reservation>
    }
}
