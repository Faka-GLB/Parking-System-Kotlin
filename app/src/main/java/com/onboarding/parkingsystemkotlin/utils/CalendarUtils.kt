package com.onboarding.parkingsystemkotlin.utils

import java.util.Calendar

object CalendarUtils {
    fun Calendar.getDateString(): String {
        var ret: String
        this.let {
            val day = it.get(Calendar.DAY_OF_MONTH)
            val month = (it.get(Calendar.MONTH) + ConstantUtils.MONTH_ADJUSTMENT)
            val year = it.get(Calendar.YEAR)
            val hour = it.get(Calendar.HOUR)
            val minute = it.get(Calendar.MINUTE)
            val amPm = if (it.get(Calendar.AM_PM) == ConstantUtils.ZERO) ConstantUtils.AM
                else ConstantUtils.PM
            ret = "$day${ConstantUtils.SLASH}$month${ConstantUtils.SLASH}$year${ConstantUtils.SPACE}$hour${ConstantUtils.COLON}$minute$amPm"
        }
        return ret
    }
}
