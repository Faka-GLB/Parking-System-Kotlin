package com.onboarding.parkingsystemkotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.databinding.ActivityShowReservationsBinding
import com.onboarding.parkingsystemkotlin.mvp.contract.ShowReservationsContract
import com.onboarding.parkingsystemkotlin.mvp.model.ShowReservationsModel
import com.onboarding.parkingsystemkotlin.mvp.presenter.ShowReservationsPresenter
import com.onboarding.parkingsystemkotlin.mvp.view.ShowReservationsView

class ShowReservationsActivity : AppCompatActivity() {
    private lateinit var presenter: ShowReservationsContract.ShowReservationsPresenter
    private lateinit var binding: ActivityShowReservationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowReservationsBinding.inflate(layoutInflater)
        presenter = ShowReservationsPresenter(view = ShowReservationsView(this, binding), model = ShowReservationsModel(ParkingDatabase))
        presenter.showReservations()
        setContentView(binding.root)
        setReturnButtonListener()
    }

    private fun setReturnButtonListener() {
        binding.buttonShowReservationsReturn.setOnClickListener { onBackPressed() }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ShowReservationsActivity::class.java)
        }
    }
}
