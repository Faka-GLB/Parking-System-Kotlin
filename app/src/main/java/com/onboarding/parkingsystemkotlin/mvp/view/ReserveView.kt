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

class ReserveView(activity: Activity, binding: ActivityNewReservationBinding) : ActivityView(activity),
    ReserveActivityContract.ReserveView {
    override fun showDateTimePicker(isStartDate: Boolean) {
        getActivity()?.let {
            val dialog = DateTimePickerDialogFragment.newInstance(isStartDate)
            dialog.show((it as AppCompatActivity).supportFragmentManager, ConstantUtils.RESERVATION_ACTIVITY_TAG)
        }
    }

    override fun returnToMainActivity() {
        getActivity()?.finish()
    }

    override fun showReserveSavedToast() {
        getActivity()?.let { Toast.makeText(it, it.getString(R.string.reservation_view_reservation_saved_toast), Toast.LENGTH_SHORT).show() }
    }
}
