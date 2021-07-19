package com.onboarding.parkingsystemkotlin.mvp.model

import com.onboarding.parkingsystemkotlin.mvp.contract.MainActivityContract
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils

class ParkingModel () : MainActivityContract.MainActivityModel {
    private var parkingLots: Int = ConstantUtils.PARKING_LOT_NOT_SET

    override fun setParkingLots(lots: Int) {
        this.parkingLots = lots
    }

    override fun getParkingLots(): Int = parkingLots
}
