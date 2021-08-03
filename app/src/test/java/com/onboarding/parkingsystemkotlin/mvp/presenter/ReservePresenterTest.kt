package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract
import com.onboarding.parkingsystemkotlin.mvp.model.ReserveModel
import com.onboarding.parkingsystemkotlin.utils.ReservationVerifier
import com.onboarding.parkingsystemkotlin.utils.CalendarUtils
import com.onboarding.parkingsystemkotlin.utils.CalendarUtils.getDateString
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Calendar
import java.util.GregorianCalendar

class ReservePresenterTest {
    private lateinit var presenter: ReserveActivityContract.ReservePresenter
    private lateinit var model: ReserveActivityContract.ReserveModel
    private val view: ReserveActivityContract.ReserveView = mock()

    @Before
    fun setUp() {
        model = ReserveModel(database = ParkingDatabase, verifier = ReservationVerifier())
        presenter = ReservePresenter(view = view, model = model)
    }

    private fun getStartDateCalendar(): Calendar {
        val calendar = GregorianCalendar()
        calendar.set(YEAR, MONTH, START_DAY, HOUR, MINUTE)
        calendar.set(Calendar.SECOND, ZERO)
        calendar.set(Calendar.MILLISECOND, ZERO)
        return calendar
    }

    private fun getEndDateCalendar(): Calendar {
        val calendar = GregorianCalendar()
        calendar.set(YEAR, MONTH, END_DAY, HOUR, MINUTE)
        calendar.set(Calendar.SECOND, ZERO)
        calendar.set(Calendar.MILLISECOND, ZERO)
        return calendar
    }

    @Test
    fun `on ok button press - empty start date`() {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT)
        whenever(view.getUserPassword()).thenReturn(USER_PASSWORD)
        presenter.onOkButtonPress()
        verify(view).showMissingStartDateToast()
    }

    @Test
    fun `on ok button press - empty end date`() {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT)
        whenever(view.getUserPassword()).thenReturn(USER_PASSWORD)
        presenter.setReservationStartDate(getStartDateCalendar())
        assertEquals(getStartDateCalendar(), model.getStartDate())
        presenter.onOkButtonPress()
        verify(view).showMissingEndDateToast()
    }

    @Test
    fun `on ok button press - empty parking lot`() {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        whenever(view.getParkingLot()).thenReturn(LOT_NOT_SET)
        whenever(view.getUserPassword()).thenReturn(USER_PASSWORD)
        presenter.setReservationStartDate(getStartDateCalendar())
        presenter.setReservationEndDate(getEndDateCalendar())
        assertEquals(getEndDateCalendar(), model.getEndDate())
        presenter.onOkButtonPress()
        verify(view).showMissingParkingLotToast()
    }

    @Test
    fun `on ok button press - empty password`() {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT)
        whenever(view.getUserPassword()).thenReturn(EMPTY_STRING_PASSWORD)
        presenter.setReservationStartDate(getStartDateCalendar())
        presenter.setReservationEndDate(getEndDateCalendar())
        presenter.onOkButtonPress()
        verify(view).showMissingUserPasswordToast()
    }

    @Test
    fun `on ok button press - lot not valid`() {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        whenever(view.getParkingLot()).thenReturn(INVALID_LOT)
        whenever(view.getUserPassword()).thenReturn(EMPTY_STRING_PASSWORD)
        presenter.setReservationStartDate(getStartDateCalendar())
        presenter.setReservationEndDate(getEndDateCalendar())
        presenter.onOkButtonPress()
        verify(view).showLotNotValidToast()
    }

    @Test
    fun `on ok button press - reservation backwards`() {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT)
        whenever(view.getUserPassword()).thenReturn(USER_PASSWORD)
        presenter.setReservationStartDate(getEndDateCalendar())
        presenter.setReservationEndDate(getStartDateCalendar())
        presenter.onOkButtonPress()
        verify(view).showReservationBackwardsToast()
    }

    @Test
    fun `on ok button press - comprobation ok`() {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT)
        whenever(view.getUserPassword()).thenReturn(USER_PASSWORD)
        presenter.setReservationStartDate(getStartDateCalendar())
        presenter.setReservationEndDate(getEndDateCalendar())
        presenter.onOkButtonPress()
        verify(view).showReserveSavedToast()
        val testReservation = model.getReservation()
        assertEquals(getStartDateCalendar(), testReservation.getStartDate())
        assertEquals(getEndDateCalendar(), testReservation.getEndDate())
        assertEquals(PARKING_LOT, testReservation.getParkingLot())
        assertEquals(USER_PASSWORD, testReservation.getUserPassword())
        verify(view).returnToMainActivity()
    }

    private fun getOverlapReservation(): Reservation {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        val reservation = Reservation()
        reservation.setStartDate(getStartDateCalendar())
        val endDate = getEndDateCalendar()
        endDate.set(Calendar.DAY_OF_MONTH, END_DAY_OVERLAP)
        reservation.setEndDate(endDate)
        reservation.setParkingLot(PARKING_LOT)
        reservation.setUserPassword(USER_PASSWORD)
        return reservation
    }

    @Test
    fun `on ok button press - reservation overlap`() {
        ParkingDatabase.setParkingLots(PARKING_LOT)
        model.addReservation(getOverlapReservation())
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT)
        whenever(view.getUserPassword()).thenReturn(USER_PASSWORD)
        presenter.setReservationStartDate(getStartDateCalendar())
        presenter.setReservationEndDate(getEndDateCalendar())
        presenter.onOkButtonPress()
        verify(view).showReservationOverlapToast()
    }

    @Test
    fun `on start date button press test`() {
        presenter.onStartDateButtonPress()
        verify(view).showDateTimePicker(isStartDate = true)
    }

    @Test
    fun `on end date button press test`() {
        presenter.onEndDateButtonPress()
        verify(view).showDateTimePicker(isStartDate = false)
    }

    @Test
    fun `on cancel button press test`() {
        presenter.onCancelButtonPress()
        verify(view).returnToMainActivity()
    }

    @Test
    fun `set reservation start date test`() {
        presenter.setReservationStartDate(getStartDateCalendar())
        assertEquals(getStartDateCalendar(), model.getReservation().getStartDate())
        verify(view).setStartDateTextView(getStartDateCalendar().getDateString())
    }

    @Test
    fun `set reservation end date test`() {
        presenter.setReservationEndDate(getEndDateCalendar())
        assertEquals(getEndDateCalendar(), model.getReservation().getEndDate())
        verify(view).setEndDateTextView(getEndDateCalendar().getDateString())
    }

    companion object {
        private const val YEAR: Int = 2021
        private const val MONTH: Int = 7
        private const val START_DAY: Int = 22
        private const val END_DAY: Int = 24
        private const val END_DAY_OVERLAP: Int = 30
        private const val HOUR: Int = 12
        private const val MINUTE: Int = 16
        private const val ZERO: Int = 0
        private const val PARKING_LOT: Int = 4
        private const val INVALID_LOT: Int = 5
        private const val LOT_NOT_SET: Int = -1
        private const val USER_PASSWORD: String = "password"
        private const val EMPTY_STRING_PASSWORD: String = ""
    }
}
