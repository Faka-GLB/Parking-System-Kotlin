package com.onboarding.parkingsystemkotlin.database

import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils
import java.util.GregorianCalendar

object ParkingDatabase {
    private val reservations: HashMap<Int, ArrayList<Reservation>> = HashMap()
    private var parkingLots: Int = ConstantUtils.PARKING_LOT_NOT_SET

    fun getReservations() = reservations

    fun addReservation(reserve: Reservation) {
        val lot = reserve.getParkingLot()
        if (reservations.containsKey(lot)) {
            this.reservations[lot]?.add(reserve)
        } else {
            val arraylist = ArrayList<Reservation>()
            arraylist.add(reserve)
            this.reservations[lot] = arraylist
        }
    }

    fun getParkingLots() = parkingLots

    fun setParkingLots(lots: Int) {
        parkingLots = lots
    }

    fun removeOldReservations(): Int {
        val currentTime = GregorianCalendar()
        var deleted: Int = ConstantUtils.ZERO
        reservations.forEach { (lot) ->
            run {
                deleted += searchForOldReservation(currentTime, reservations[lot], lot)
                if (reservations[lot].isNullOrEmpty()) {
                    reservations.remove(lot)
                }
            }
        }
        return deleted
    }

    private fun searchForOldReservation(current: GregorianCalendar, reservations: ArrayList<Reservation>?, lot: Int): Int {
        val reservationsAux: ArrayList<Reservation>? = reservations
        this.reservations[lot] = reservationsAux?.filter { current.before(it.getEndDate()) } as java.util.ArrayList<Reservation>
        return reservations.filter { current.after(it.getEndDate()) }.size
    }
}
