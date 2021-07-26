package com.onboarding.parkingsystemkotlin.mvp.model

import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.mvp.contract.MainActivityContract

class ParkingModel(private val database: ParkingDatabase) : MainActivityContract.MainActivityModel {

    override fun setParkingLots(lots: Int) {
        this.database.setParkingLots(lots)
    }

    override fun getParkingLots(): Int = database.getParkingLots()

    override fun removeOldReservations(): Int {
        return database.removeOldReservations()
    }
}
