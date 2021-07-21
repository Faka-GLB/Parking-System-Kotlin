package com.onboarding.parkingsystemkotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onboarding.parkingsystemkotlin.databinding.ActivityNewReservationBinding
import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract
import com.onboarding.parkingsystemkotlin.mvp.presenter.ReservePresenter
import com.onboarding.parkingsystemkotlin.mvp.view.ReserveView

class ReserveActivity : AppCompatActivity(){
    private lateinit var presenter: ReserveActivityContract.ReservePresenter
    private lateinit var binding: ActivityNewReservationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ReservePresenter(view = ReserveView(this, binding))
        setListeners()
    }

    private fun setListeners() {
        binding.btnNewReservationActivityStartDate.setOnClickListener { this.presenter.onStartDateButtonPress() }
        binding.btnNewReservationActivityEndDate.setOnClickListener { this.presenter.onEndDateButtonPress() }
        binding.btnNewReservationActivityOk.setOnClickListener { this.presenter.onOkButtonPress() }
        binding.btnNewReservationActivityCancel.setOnClickListener { this.presenter.onCancelButtonPress() }
    }

    companion object{
        fun getIntent(context: Context): Intent {
            return Intent(context, ReserveActivity::class.java)
        }
    }
}
