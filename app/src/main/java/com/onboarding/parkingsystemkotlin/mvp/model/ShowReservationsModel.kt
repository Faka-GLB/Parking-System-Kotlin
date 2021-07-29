package com.onboarding.parkingsystemkotlin.mvp.model

import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.mvp.contract.ShowReservationsContract

class ShowReservationsModel(private val database: ParkingDatabase) : ShowReservationsContract.ShowReservationsModel {

    override fun getReservationStartDateString(reservation: Reservation): String = reservation.getStartDateString()

    override fun getReservationEndDateString(reservation: Reservation): String = reservation.getEndDateString()

    override fun getReservationParkingLotString(reservation: Reservation) = reservation.getParkingLot().toString()

    override fun listReservations(): List<Reservation> {
        val list: ArrayList<Reservation> = ArrayList()
        with (database.getReservations()) {
            this.forEach { (lot) ->
                this[lot]?.let { list.addAll(it) }
            }
        }
        return list.toList()
    }
}
