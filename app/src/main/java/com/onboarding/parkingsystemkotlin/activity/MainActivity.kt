package com.onboarding.parkingsystemkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onboarding.parkingsystemkotlin.databinding.ActivityMainBinding
import com.onboarding.parkingsystemkotlin.listener.OnInputListener
import com.onboarding.parkingsystemkotlin.mvp.contract.MainActivityContract
import com.onboarding.parkingsystemkotlin.mvp.model.ParkingModel
import com.onboarding.parkingsystemkotlin.mvp.presenter.ParkingPresenter
import com.onboarding.parkingsystemkotlin.mvp.view.ParkingView

class MainActivity : AppCompatActivity(), OnInputListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainActivityContract.MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ParkingPresenter(model = ParkingModel(), view = ParkingView(this))
        setListeners()
    }

    private fun setListeners() {
        binding.buttonMainSetParkingSpaces.setOnClickListener { presenter.onSetParkingButtonPressed() }
    }

    override fun sendInput(input: Int) {
        presenter.setParkingLots(input)
    }
}
