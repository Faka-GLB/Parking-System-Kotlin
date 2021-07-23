package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract
import com.onboarding.parkingsystemkotlin.mvp.model.ReserveModel
import com.onboarding.parkingsystemkotlin.utils.ConstantUtils
import com.onboarding.parkingsystemkotlin.utils.ReservationVerifier
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

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
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT)
        whenever(view.getUserPassword()).thenReturn(USER_PASSWORD)
        presenter.onOkButtonPress()
        verify(view).showMissingStartDateToast()
    }

    @Test
    fun `on ok button press - empty end date`() {
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT)
        whenever(view.getUserPassword()).thenReturn(USER_PASSWORD)
        presenter.setReservationStartDate(getStartDateCalendar())
        assertEquals(getStartDateCalendar(), model.getStartDate())
        presenter.onOkButtonPress()
        verify(view).showMissingEndDateToast()
    }

    @Test
    fun `on ok button press - empty parking lot`() {
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
        whenever(view.getParkingLot()).thenReturn(PARKING_LOT)
        whenever(view.getUserPassword()).thenReturn(EMPTY_STRING_PASSWORD)
        presenter.setReservationStartDate(getStartDateCalendar())
        presenter.setReservationEndDate(getEndDateCalendar())
        presenter.onOkButtonPress()
        verify(view).showMissingUserPasswordToast()
    }

    @Test
    fun `on ok button press - comprobation ok`() {
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

    private fun getDateString(date: Calendar?): String {
        var ret = ConstantUtils.EMPTY_STRING
        if (date != null) {
            ret = (date.get(Calendar.DAY_OF_MONTH).toString() + ConstantUtils.SLASH +
                    (date.get(Calendar.MONTH) + ConstantUtils.MONTH_ADJUSTMENT) + ConstantUtils.SLASH +
                    date.get(Calendar.YEAR) + ConstantUtils.SPACE +
                    date.get(Calendar.HOUR) + ConstantUtils.COLON +
                    date.get(Calendar.MINUTE))
        }
        return ret
    }

    @Test
    fun `set reservation start date test`() {
        presenter.setReservationStartDate(getStartDateCalendar())
        assertEquals(getStartDateCalendar(), model.getReservation().getStartDate())
        verify(view).setStartDateTextView(getDateString(getStartDateCalendar()))
    }

    @Test
    fun `set reservation end date test`() {
        presenter.setReservationEndDate(getEndDateCalendar())
        assertEquals(getEndDateCalendar(), model.getReservation().getEndDate())
        verify(view).setEndDateTextView(getDateString(getEndDateCalendar()))
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
        private const val LOT_NOT_SET: Int = -1
        private const val USER_PASSWORD: String = "password"
        private const val EMPTY_STRING_PASSWORD: String = ""
    }
}
