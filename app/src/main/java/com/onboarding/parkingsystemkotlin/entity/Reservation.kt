package com.onboarding.parkingsystemkotlin.entity

import com.onboarding.parkingsystemkotlin.utils.ConstantUtils
import java.util.Calendar

class Reservation() {
    private var startDate: Calendar? = null
    private var endDate: Calendar? = null
    private var parkingLot: Int = ConstantUtils.PARKING_LOT_NOT_SET
    private lateinit var userPassword: String

    fun getStartDate() = startDate

    fun getEndDate() = endDate

    fun setStartDate(startDate: Calendar) {
        this.startDate = startDate
    }

    fun setEndDate(endDate: Calendar) {
        this.endDate = endDate
    }

    fun getParkingLot() = parkingLot

    fun getUserPassword() = userPassword

    fun setParkingLot(parkingLot: Int) {
        this.parkingLot = parkingLot
    }

    fun setUserPassword(userPassword: String) {
        this.userPassword = userPassword
    }
}
