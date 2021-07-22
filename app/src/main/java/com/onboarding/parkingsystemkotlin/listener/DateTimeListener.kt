package com.onboarding.parkingsystemkotlin.listener

import java.util.Calendar

interface DateTimeListener {
    fun sendStartDateTime(dateTimeCalendar: Calendar)
    fun sendEndDateTime(dateTimeCalendar: Calendar)
}
