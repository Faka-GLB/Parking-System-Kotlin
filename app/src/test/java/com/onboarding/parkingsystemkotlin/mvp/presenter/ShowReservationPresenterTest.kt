package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.onboarding.parkingsystemkotlin.database.ParkingDatabase
import com.onboarding.parkingsystemkotlin.entity.Reservation
import com.onboarding.parkingsystemkotlin.mvp.contract.ShowReservationsContract
import com.onboarding.parkingsystemkotlin.mvp.model.ShowReservationsModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ShowReservationPresenterTest {
    private lateinit var presenter: ShowReservationsContract.ShowReservationsPresenter
    private lateinit var model: ShowReservationsContract.ShowReservationsModel
    private val view: ShowReservationsContract.ShowReservationsView = mock()

    @Before
    fun setUp() {
        model = ShowReservationsModel(database = ParkingDatabase)
        presenter = ShowReservationsPresenter(view = view, model = model)
    }

    @Test
    fun `on show reservations`() {
        presenter.showReservations()
        verify(view).showReservations(model.listReservations())
        assertEquals(expectedList(), model.listReservations())
    }

    private fun expectedList(): List<Reservation> {
        val list: ArrayList<Reservation> = ArrayList()
        val reservations = ParkingDatabase.getReservations()
        reservations.forEach { (lot) ->
            reservations[lot]?.let { list.addAll(it) }
        }
        return list.toList()
    }
}
