package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract
import com.onboarding.parkingsystemkotlin.utils.CalendarUtils
import com.onboarding.parkingsystemkotlin.utils.CalendarUtils.getDateString
import com.onboarding.parkingsystemkotlin.utils.ReserveComprobation
import java.util.Calendar

class ReservePresenter(private val view: ReserveActivityContract.ReserveView, private val model: ReserveActivityContract.ReserveModel) :
    ReserveActivityContract.ReservePresenter {

    override fun onStartDateButtonPress() {
        view.showDateTimePicker(true)
    }

    override fun onEndDateButtonPress() {
        view.showDateTimePicker(false)
    }

    override fun onOkButtonPress() {
        model.setReservationInfo(view.getParkingLot(), view.getUserPassword())
        val reservation = model.getReservation()
        when (model.reserveFieldsCheck(reservation)) {
            ReserveComprobation.MISSING_START -> view.showMissingStartDateToast()
            ReserveComprobation.MISSING_END -> view.showMissingEndDateToast()
            ReserveComprobation.MISSING_LOT -> view.showMissingParkingLotToast()
            ReserveComprobation.LOT_NOT_VALID -> view.showLotNotValidToast()
            ReserveComprobation.MISSING_PASSWORD -> view.showMissingUserPasswordToast()
            ReserveComprobation.RESERVATION_BACKWARDS -> view.showReservationBackwardsToast()
            ReserveComprobation.RESERVATION_OVERLAP -> view.showReservationOverlapToast()
            ReserveComprobation.COMPROBATION_OK -> {
                view.showReserveSavedToast()
                model.addReservation(reservation)
                view.returnToMainActivity()
            }
        }
    }

    override fun onCancelButtonPress() {
        view.returnToMainActivity()
    }

    override fun setReservationStartDate(startDate: Calendar) {
        model.setStartDate(startDate)
        model.getStartDate()?.let { view.setStartDateTextView(it.getDateString()) }
    }

    override fun setReservationEndDate(endDate: Calendar) {
        model.setEndDate(endDate)
        model.getEndDate()?.let { view.setEndDateTextView(it.getDateString()) }
    }
}
