package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils
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
            ReserveComprobation.MISSING_PASSWORD -> view.showMissingUserPasswordToast()
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
        view.setStartDateTextView(getDateString(model.getStartDate()))
    }

    override fun setReservationEndDate(endDate: Calendar) {
        model.setEndDate(endDate)
        view.setEndDateTextView(getDateString(model.getEndDate()))
    }

    private fun getDateString(date: Calendar?): String {
        var ret = ConstantUtils.EMPTY_STRING
        date?.let {
            val day = it.get(Calendar.DAY_OF_MONTH)
            val month = (it.get(Calendar.MONTH) + ConstantUtils.MONTH_ADJUSTMENT)
            val year = it.get(Calendar.YEAR)
            val hour = it.get(Calendar.HOUR)
            val minute = it.get(Calendar.MINUTE)
            ret = "$day${ConstantUtils.SLASH}$month${ConstantUtils.SLASH}$year${ConstantUtils.SPACE}$hour${ConstantUtils.COLON}$minute"
        }
        return ret
    }
}
