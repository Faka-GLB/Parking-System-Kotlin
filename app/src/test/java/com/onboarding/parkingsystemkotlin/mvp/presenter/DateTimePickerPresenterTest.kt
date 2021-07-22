package com.onboarding.parkingsystemkotlin.mvp.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.onboarding.parkingsystemkotlin.listener.DateTimeListener
import com.onboarding.parkingsystemkotlin.mvp.contract.DateTimePickerContract
import org.junit.Before
import org.junit.Test

class DateTimePickerPresenterTest {
    private lateinit var presenter: DateTimePickerPresenter
    private val view: DateTimePickerContract.DateTimePickerView = mock()
    private val listener: DateTimeListener = mock()

    @Before
    fun setUp() {
        presenter = DateTimePickerPresenter(view = view, isStartButton = true)
    }

    @Test
    fun `on ok button press test - true`() {
        presenter.onOkButtonPress(listener)
        verify(view).sendStartDateTime(listener)
        verify(view).dismissDateTimePicker()
    }

    @Test
    fun `on ok button press test - false`() {
        val presenterFalse = DateTimePickerPresenter(view = view, isStartButton = false)
        presenterFalse.onOkButtonPress(listener)
        verify(view).sendEndDateTime(listener)
        verify(view).dismissDateTimePicker()
    }

    @Test
    fun `on cancel button press test`() {
        presenter.onCancelButtonPress()
        verify(view).dismissDateTimePicker()
    }
}
