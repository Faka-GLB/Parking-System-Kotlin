package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.onboarding.parkingsystemkotlin.listener.OnInputListener
import com.onboarding.parkingsystemkotlin.mvp.contract.ConfigureParkingLotsContract
import org.junit.Before
import org.junit.Test

class ConfigureParkingPresenterTest {
    private lateinit var presenter: ConfigureParkingLotsContract.ConfigureParkingPresenter
    private val view: ConfigureParkingLotsContract.ConfigureParkingView = mock()
    private val listener: OnInputListener = mock()

    @Before
    fun setup() {
        presenter = ConfigureParkingPresenter(view = view, inputListener = listener)
    }

    @Test
    fun `on cancel button press test`() {
        presenter.onCancelButtonPress()
        verify(view).closeDialog()
    }

    @Test
    fun `on ok button press test pass`() {
        whenever(view.getLots()).thenReturn(PARKING_LOT_STRING)
        presenter.onOkButtonPress()
        verify(view).onLotsNotEmpty(PARKING_LOT, listener)
    }

    @Test
    fun `on ok button press test empty`() {
        whenever(view.getLots()).thenReturn(EMPTY_STRING_LOT)
        presenter.onOkButtonPress()
        verify(view).showEmptyInputToast()
    }

    companion object {
        private const val PARKING_LOT_STRING: String = "4"
        private const val PARKING_LOT: Int = 4
        private const val EMPTY_STRING_LOT = ""
    }
}
