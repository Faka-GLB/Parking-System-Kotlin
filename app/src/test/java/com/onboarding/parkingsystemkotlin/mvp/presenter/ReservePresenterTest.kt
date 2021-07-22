package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.onboarding.parkingsystemkotlin.mvp.contract.ReserveActivityContract
import org.junit.Before
import org.junit.Test

class ReservePresenterTest {
    private lateinit var presenter: ReserveActivityContract.ReservePresenter
    private val view: ReserveActivityContract.ReserveView = mock()

    @Before
    fun setUp() {
        presenter = ReservePresenter(view = view)
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
    fun `on ok button press test`() {
        presenter.onOkButtonPress()
        verify(view).showReserveSavedToast()
        verify(view).returnToMainActivity()
    }

    @Test
    fun `on cancel button press test`() {
        presenter.onCancelButtonPress()
        verify(view).returnToMainActivity()
    }
}
