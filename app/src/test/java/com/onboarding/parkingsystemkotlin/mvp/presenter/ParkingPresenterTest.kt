package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.mvp.contract.MainActivityContract
import com.onboarding.parkingsystemkotlin.mvp.model.ParkingModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Calendar
import java.util.GregorianCalendar

class ParkingPresenterTest {
    private lateinit var presenter: MainActivityContract.MainActivityPresenter
    private var model: MainActivityContract.MainActivityModel = ParkingModel(database = ParkingDatabase)
    private val view: MainActivityContract.MainActivityView = mock()
    private val startDate: GregorianCalendar = mock()

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
    fun `new reservation`() {
        presenter.onNewReservationButtonPressed()
        assertEquals(ZERO, model.removeOldReservations())
        verify(view).showNewReservationActivity()
    }

    @Test
    fun `remove old reservations test`() {
        presenter.onRemoveOldReservationsButtonPressed()
        assertEquals(ZERO, model.removeOldReservations())
        verify(view).showReservationsRemovedToast()
    }

    @Test
    fun `show reservations test`() {
        presenter.onShowReservationsButtonPressed()
        verify(view).showReservations()
    }

    private fun getEndDateCalendar(): Calendar {
        val calendar = GregorianCalendar()
        calendar.set(
            YEAR,
            MONTH,
            DAY,
            HOUR,
            MINUTE
        )
        return calendar
    }

    private fun getTestReservation(): Reservation {
        val reservationTest = Reservation()
        reservationTest.setStartDate(startDate)
        reservationTest.setEndDate(getEndDateCalendar())
        reservationTest.setParkingLot(PARKING_LOT)
        reservationTest.setUserPassword(PASSWORD)
        return reservationTest
    }

    @Test
    fun `on remove old reservations test - removal ok`() {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        ParkingDatabase.addReservation(getTestReservation())
        assertEquals(ONE, ParkingDatabase.getReservationsAmount())
        presenter.onRemoveOldReservationsButtonPressed()
        assertEquals(ZERO, ParkingDatabase.getReservationsAmount())
    }

    companion object {
        private const val PARKING_LOT: Int = 4
        private const val ZERO: Int = 0
        private const val YEAR: Int = 2021
        private const val MONTH: Int = 7
        private const val DAY: Int = 1
        private const val HOUR: Int = 10
        private const val MINUTE: Int = 18
        private const val PASSWORD: String = "password"
        private const val ONE: Int = 1
    }
}
