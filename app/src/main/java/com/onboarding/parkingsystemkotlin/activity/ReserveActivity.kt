package com.onboarding.parkingsystemkotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.databinding.ActivityNewReservationBinding
import com.onboarding.parkingsystemkotlin.listener.DateTimeListener
import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract
import com.onboarding.parkingsystemkotlin.mvp.model.ReserveModel
import com.onboarding.parkingsystemkotlin.mvp.presenter.ReservePresenter
import com.onboarding.parkingsystemkotlin.mvp.view.ReserveView
import com.onboarding.parkingsystemkotlin.utils.ReservationVerifier
import java.util.Calendar

class ReserveActivity : AppCompatActivity(), DateTimeListener {
    private lateinit var presenter: ReserveActivityContract.ReservePresenter
    private lateinit var binding: ActivityNewReservationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ReservePresenter(
            view = ReserveView(this, binding),
            model = ReserveModel(database = ParkingDatabase, verifier = ReservationVerifier())
        )
        setListeners()
    }

    private fun setListeners() {
        binding.buttonNewReservationActivityStartDate.setOnClickListener { this.presenter.onStartDateButtonPress() }
        binding.buttonNewReservationActivityEndDate.setOnClickListener { this.presenter.onEndDateButtonPress() }
        binding.buttonNewReservationActivityOk.setOnClickListener { this.presenter.onOkButtonPress() }
        binding.buttonNewReservationActivityCancel.setOnClickListener { this.presenter.onCancelButtonPress() }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ReserveActivity::class.java)
        }
    }

    override fun sendStartDateTime(dateTimeCalendar: Calendar) {
        presenter.setReservationStartDate(dateTimeCalendar)
    }

    override fun sendEndDateTime(dateTimeCalendar: Calendar) {
        presenter.setReservationEndDate(dateTimeCalendar)
    }
}
