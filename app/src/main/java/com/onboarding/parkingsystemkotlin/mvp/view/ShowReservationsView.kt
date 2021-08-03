package com.onboarding.parkingsystemkotlin.mvp.view

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.onboarding.parkingsystemkotlin.adapter.ReservationAdapter
import com.onboarding.parkingsystemkotlin.databinding.ActivityShowReservationsBinding
import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.mvp.contract.ShowReservationsContract
import com.onboarding.parkingsystemkotlin.mvp.view.base.ActivityView

class ShowReservationsView(activity: Activity, private val binding: ActivityShowReservationsBinding) :
    ActivityView(activity), ShowReservationsContract.ShowReservationsView {

    override fun showReservations(list: List<Reservation>) {
        binding.recyclerViewShowReservationsActivity.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewShowReservationsActivity.adapter = ReservationAdapter(list)
    }
}
