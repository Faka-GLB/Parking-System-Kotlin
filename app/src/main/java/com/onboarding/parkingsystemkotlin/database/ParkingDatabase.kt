package com.onboarding.parkingsystemkotlin.database

import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils

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

    fun setParkingLots(lots: Int){
        parkingLots = lots
    }
}
