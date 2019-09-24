package com.uziassantosferreira.simplescreenstate.extensions

import com.uziassantosferreira.simplescreenstate.ui.model.SimpleScreenState
import com.uziassantosferreira.simplescreenstate.throwable.NetworkException

fun Throwable.getScreenState(): SimpleScreenState =
    when (this) {
        is NetworkException -> {
            SimpleScreenState.NetworkError
        }
        else -> SimpleScreenState.GenericError
    }