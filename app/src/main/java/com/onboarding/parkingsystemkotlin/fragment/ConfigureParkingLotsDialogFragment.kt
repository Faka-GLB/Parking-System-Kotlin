package com.onboarding.parkingsystemkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.onboarding.parkingsystemkotlin.databinding.ConfigureParkingLotsDialogFragmentMainActivityBinding
import com.onboarding.parkingsystemkotlin.listener.OnInputListener
import com.onboarding.parkingsystemkotlin.mvp.contract.ConfigureParkingLotsContract
import com.onboarding.parkingsystemkotlin.mvp.presenter.ConfigureParkingPresenter
import com.onboarding.parkingsystemkotlin.mvp.view.ConfigureParkingView
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils

class ConfigureParkingLotsDialogFragment : DialogFragment() {
    private lateinit var presenter: ConfigureParkingLotsContract.ConfigureParkingPresenter
    private lateinit var binding: ConfigureParkingLotsDialogFragmentMainActivityBinding
    private lateinit var inputListener: OnInputListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ConfigureParkingLotsDialogFragmentMainActivityBinding.inflate(layoutInflater)
        this.presenter = ConfigureParkingPresenter(inputListener = this.inputListener, view = ConfigureParkingView(this, this.binding))
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonDialogFragmentOk.setOnClickListener { presenter.onOkButtonPress() }
        binding.buttonDialogFragmentCancel.setOnClickListener { presenter.onCancelButtonPress() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            inputListener = activity as OnInputListener
        } catch (e: ClassCastException) {
            Log.e(ConstantUtils.CONFIGURE_PARKING_LOTS_DIALOG_TAG, e.javaClass.canonicalName + e.message)
        }
    }
}
