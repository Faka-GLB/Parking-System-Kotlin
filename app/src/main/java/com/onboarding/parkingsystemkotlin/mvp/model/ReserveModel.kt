package com.onboarding.parkingsystemkotlin.mvp.model

import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract
import com.onboarding.parkingsystemkotlin.utils.ReservationVerifier
import com.onboarding.parkingsystemkotlin.utils.ReserveComprobation
import java.util.Calendar

class ReserveModel(private val database: ParkingDatabase, private val verifier: ReservationVerifier) :
    ReserveActivityContract.ReserveModel {
    private val reservation = Reservation()

    override fun addReservation(reservation: Reservation) {
        database.addReservation(reservation)
    }

    override fun getReservation(): Reservation {
        return reservation
    }

    override fun setStartDate(startDate: Calendar) {
        reservation.setStartDate(startDate)
    }

    override fun setEndDate(endDate: Calendar) {
        reservation.setEndDate(endDate)
    }

    override fun reserveFieldsCheck(reservation: Reservation): ReserveComprobation {
        return verifier.verifyReservation(reservation)
    }

    override fun setReservationInfo(parkingLot: Int, userPassword: String) {
        reservation.setParkingLot(parkingLot)
        reservation.setUserPassword(userPassword)
    }

    override fun getStartDate(): Calendar? {
        return reservation.getStartDate()
    }

    override fun getEndDate(): Calendar? {
        return reservation.getEndDate()
    }
}
