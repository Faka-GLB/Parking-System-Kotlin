package com.onboarding.parkingsystemkotlin.mvp.view.base

import android.content.Context
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

open class FragmentView(fragment: Fragment) {
    private val fragmentRef: WeakReference<Fragment> = WeakReference<Fragment>(fragment)

    fun getFragment(): Fragment? {
        return fragmentRef.get()
    }

    fun getContext(): Context? {
        return getFragment()?.context
    }
}
