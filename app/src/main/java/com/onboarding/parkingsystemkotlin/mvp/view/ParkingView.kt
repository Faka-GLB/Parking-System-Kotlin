package com.onboarding.parkingsystemkotlin.mvp.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.onboarding.parkingsystemkotlin.fragment.ConfigureParkingLotsDialogFragment
import com.onboarding.parkingsystemkotlin.mvp.contract.MainActivityContract
import com.onboarding.parkingsystemkotlin.mvp.view.base.ActivityView
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils

class ParkingView(activity: Activity) : ActivityView(activity = activity), MainActivityContract.MainActivityView {

    override fun showConfigureParkingLotsDialogFragment() {
        getActivity()?.let {
            ConfigureParkingLotsDialogFragment().show((it as AppCompatActivity).supportFragmentManager, ConstantUtils.PARKING_VIEW_TAG)
        }
    }
}
