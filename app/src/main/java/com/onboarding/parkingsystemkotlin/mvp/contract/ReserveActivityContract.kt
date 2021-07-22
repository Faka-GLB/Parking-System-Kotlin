package com.onboarding.parkingsystemkotlin.mvp.contract

import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.utils.ReserveComprobation
import java.util.Calendar

interface ReserveActivityContract {
    interface ReservePresenter {
        fun onStartDateButtonPress()
        fun onEndDateButtonPress()
        fun onOkButtonPress()
        fun onCancelButtonPress()
        fun setReservationStartDate(startDate: Calendar)
        fun setReservationEndDate(endDate: Calendar)
    }

    interface ReserveView {
        fun showDateTimePicker(isStartDate: Boolean)
        fun returnToMainActivity()
        fun setStartDateTextView(date: String)
        fun setEndDateTextView(date: String)
        fun getParkingLot(): Int
        fun getUserPassword(): String
        fun showMissingStartDateToast()
        fun showMissingEndDateToast()
        fun showMissingParkingLotToast()
        fun showMissingUserPasswordToast()
        fun showReservationOverlapToast()
        fun showReserveSavedToast()
    }

    interface ReserveModel {
        fun addReservation(reservation: Reservation)
        fun getReservation(): Reservation
        fun setStartDate(startDate: Calendar)
        fun setEndDate(endDate: Calendar)
        fun reserveFieldsCheck(reservation: Reservation): ReserveComprobation
        fun setReservationInfo(parkingLot: Int, userPassword: String)
        fun getStartDate(): Calendar?
        fun getEndDate(): Calendar?
    }
}
