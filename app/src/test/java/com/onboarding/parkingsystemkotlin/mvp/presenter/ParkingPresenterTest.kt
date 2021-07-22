package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.onboarding.parkingsystemkotlin.mvp.contract.MainActivityContract
import com.onboarding.parkingsystemkotlin.mvp.model.ParkingModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ParkingPresenterTest {
    private lateinit var presenter: MainActivityContract.MainActivityPresenter
    private var model: MainActivityContract.MainActivityModel = ParkingModel()
    private val view: MainActivityContract.MainActivityView = mock()

    @Before
    fun setUp() {
        presenter = ParkingPresenter(model = model, view = view)
    }

    @Test
    fun `on set parking button pressed`() {
        presenter.onSetParkingButtonPressed()
        verify(view).showConfigureParkingLotsDialogFragment()
    }

    @Test
    fun `set parking lots`() {
        presenter.setParkingLots(PARKING_LOT)
        assertEquals(PARKING_LOT, model.getParkingLots())
    }

    @Test
    fun `new reservation` (){
        presenter.onNewReservationButtonPressed()
        verify(view).showNewReservationActivity()
    }

    companion object {
        private const val PARKING_LOT: Int = 4
    }
}
