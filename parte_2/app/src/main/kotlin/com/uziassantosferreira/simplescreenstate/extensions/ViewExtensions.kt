package com.uziassantosferreira.simplescreenstate.extensions

import android.view.View

var View.hidden: Boolean
    get() = visibility == View.GONE
    set(hide) {
        visibility = if (hide) View.GONE else View.VISIBLE
    }