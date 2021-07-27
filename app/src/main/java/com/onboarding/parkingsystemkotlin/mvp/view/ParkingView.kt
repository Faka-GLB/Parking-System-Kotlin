package com.onboarding.parkingsystemkotlin.mvp.view

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.onboarding.parkingsystemkotlin.R
import com.onboarding.parkingsystemkotlin.activity.ReserveActivity
import com.onboarding.parkingsystemkotlin.fragment.ConfigureParkingLotsDialogFragment
import com.onboarding.parkingsystemkotlin.mvp.contract.MainActivityContract
import com.onboarding.parkingsystemkotlin.mvp.view.base.ActivityView
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils

class ParkingView(activity: Activity) : ActivityView(activity = activity), MainActivityContract.MainActivityView {

    override fun showConfigureParkingLotsDialogFragment() {
        activity?.let {
            ConfigureParkingLotsDialogFragment().show((it as AppCompatActivity).supportFragmentManager, ConstantUtils.PARKING_VIEW_TAG)
        }
    }

    override fun showNewReservationActivity() {
        activity?.let {
            it.startActivity(ReserveActivity.getIntent(it))
        }
    }

    override fun showReservationsRemovedToast() {
        activity?.let {
            Toast.makeText(it, it.getString(R.string.main_activity_reservations_removed_toast), Toast.LENGTH_SHORT).show()
        }
    }
}
