package com.onboarding.parkingsystemkotlin.mvp.view

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.onboarding.parkingsystemkotlin.R
import com.onboarding.parkingsystemkotlin.databinding.ActivityNewReservationBinding
import com.onboarding.parkingsystemkotlin.fragment.DateTimePickerDialogFragment
import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract
import com.onboarding.parkingsystemkotlin.mvp.view.base.ActivityView
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils

class ReserveView(activity: Activity, private val binding: ActivityNewReservationBinding) : ActivityView(activity),
    ReserveActivityContract.ReserveView {
    override fun showDateTimePicker(isStartDate: Boolean) {
        activity?.let {
            val dialog = DateTimePickerDialogFragment.newInstance(isStartDate)
            dialog.show((it as AppCompatActivity).supportFragmentManager, ConstantUtils.RESERVATION_ACTIVITY_TAG)
        }
    }

    override fun returnToMainActivity() {
        activity?.finish()
    }

    override fun setStartDateTextView(date: String) {
        binding.textViewNewReservationActivityStartDate.text = date
    }

    override fun setEndDateTextView(date: String) {
        binding.textViewNewReservationActivityEndDate.text = date
    }

    override fun getParkingLot(): Int {
        val lot: String = binding.editTextNewReservationActivityLotNumber.text.toString()
        return if (lot.isEmpty()) {
            ConstantUtils.PARKING_LOT_NOT_SET
        } else {
            lot.toInt()
        }
    }

    override fun getUserPassword() = binding.editTextNewReservationActivityPassword.text.toString()

    override fun showMissingStartDateToast() {
        showComprobationToast(R.string.reservation_view_missing_start_date_toast)
    }

    override fun showMissingEndDateToast() {
        showComprobationToast(R.string.reservation_view_missing_end_date_toast)
    }

    override fun showMissingParkingLotToast() {
        showComprobationToast(R.string.reservation_view_missing_parking_lot_toast)
    }

    override fun showMissingUserPasswordToast() {
        showComprobationToast(R.string.reservation_view_missing_user_password_toast)
    }

    override fun showReservationOverlapToast() {
        showComprobationToast(R.string.reservation_view_reservation_overlap_toast)
    }

    override fun showReserveSavedToast() {
        showComprobationToast(R.string.reservation_view_reservation_saved_toast)
    }

    override fun showLotNotValidToast() {
        showComprobationToast(R.string.reservation_view_lot_not_valid_toast)
    }

    override fun showReservationBackwardsToast() {
        showComprobationToast(R.string.reservation_view_reservation_backwards_toast)
    }

    private fun showComprobationToast(messageId: Int) {
        activity?.let { Toast.makeText(it, it.getString(messageId), Toast.LENGTH_SHORT).show() }
    }
}
