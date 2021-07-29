package com.onboarding.parkingsystemkotlin.utils

import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.entity.Reservation

class ReservationVerifier(private val database: ParkingDatabase = ParkingDatabase) {

    fun verifyReservation(reservation: Reservation): ReserveComprobation {
        if (reservation.getStartDate() == null) return ReserveComprobation.MISSING_START
        if (reservation.getEndDate() == null) return ReserveComprobation.MISSING_END
        if (reservation.getParkingLot() == ConstantUtils.PARKING_LOT_NOT_SET) return ReserveComprobation.MISSING_LOT
        if (reservation.getParkingLot() > ParkingDatabase.getParkingLots()) return ReserveComprobation.LOT_NOT_VALID
        if (reservation.getUserPassword().isEmpty()) return ReserveComprobation.MISSING_PASSWORD
        if (isReservationBackwards(reservation)) return ReserveComprobation.RESERVATION_BACKWARDS
        if (isReservationOk(reservation).not()) return ReserveComprobation.RESERVATION_OVERLAP
        return ReserveComprobation.COMPROBATION_OK
    }

    private fun isReservationBackwards(reservation: Reservation) = reservation.getEndDate()?.before(reservation.getStartDate()) == true

    private fun isReservationOk(reserve: Reservation): Boolean {
        val lot = reserve.getParkingLot()
        if (database.getReservations().containsKey(lot)) {
            val lotReservations: java.util.ArrayList<Reservation>? = database.getReservations()[lot]
            return checkArrayList(lotReservations, reserve)
        } else {
            return true
        }
    }

    private fun checkArrayList(arrayList: ArrayList<Reservation>?, reserve: Reservation): Boolean {
        if (arrayList != null) {
            for (storedReservation in arrayList) {
                if (isOverlapping(storedReservation, reserve)) {
                    return false
                }
            }
        }
        return true
    }

    private fun isOverlapping(reservation: Reservation, anotherReservation: Reservation): Boolean {
        val reservationStartDate = reservation.getStartDate()
        val reservationEndDate = reservation.getEndDate()
        val anotherReservationStartDate = anotherReservation.getStartDate()
        val anotherReservationEndDate = anotherReservation.getEndDate()

        //start or full overlap
        if (reservationStartDate?.before(anotherReservationStartDate) == true &&
            reservationEndDate?.after(anotherReservationStartDate) == true
        ) {
            return true
        }
        //end overlap
        if (reservationStartDate?.before(anotherReservationEndDate) == true &&
            reservationEndDate?.after(anotherReservationEndDate) == true
        ) {
            return true
        } else {
            //stored reservation full overlaps new reservation
            return (reservationStartDate?.after(anotherReservationStartDate) == true &&
                    reservationEndDate?.before(anotherReservationEndDate) == true)
        }
    }
}
