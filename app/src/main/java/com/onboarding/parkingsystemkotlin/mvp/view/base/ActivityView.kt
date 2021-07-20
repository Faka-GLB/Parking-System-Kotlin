package com.onboarding.parkingsystemkotlin.mvp.view.base

import android.app.Activity
import java.lang.ref.WeakReference

open class ActivityView(activity: Activity) {
    private val activityRef: WeakReference<Activity> = WeakReference<Activity>(activity)

    fun getActivity(): Activity? = activityRef.get()
}