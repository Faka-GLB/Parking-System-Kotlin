package com.onboarding.parkingsystemkotlin.mvp.view

import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.onboarding.parkingsystemkotlin.R
import com.onboarding.parkingsystemkotlin.databinding.ConfigureParkingLotsDialogFragmentMainActivityBinding
import com.onboarding.parkingsystemkotlin.listener.OnInputListener
import com.onboarding.parkingsystemkotlin.mvp.contract.ConfigureParkingLotsContract
import com.onboarding.parkingsystemkotlin.mvp.view.base.FragmentView

class ConfigureParkingView(
    fragment: DialogFragment,
    private val binding: ConfigureParkingLotsDialogFragmentMainActivityBinding
) : FragmentView(fragment), ConfigureParkingLotsContract.ConfigureParkingView {

    override fun getLots(): String = binding.editTextDialogFragmentParkingInput.text.toString()

    override fun closeDialog() {
        (fragment as DialogFragment?)?.dismiss()
    }

    override fun showEmptyInputToast() {
        context?.let {
            showToast(it.getString(R.string.fragment_configure_parking_lots_toast_empty_input))
        }
    }

    override fun onLotsNotEmpty(lots: Int, inputListener: OnInputListener) {
        inputListener.sendInput(lots)
        context?.let {
            showToast(it.getString(R.string.fragment_configure_parking_lots_toast_confirm, lots))
        }
        closeDialog()
    }

    private fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}
