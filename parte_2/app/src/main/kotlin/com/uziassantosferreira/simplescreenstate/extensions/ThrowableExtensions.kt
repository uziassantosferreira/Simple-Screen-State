package com.uziassantosferreira.simplescreenstate.extensions

import com.uziassantosferreira.simplescreenstate.throwable.NetworkException
import com.uziassantosferreira.simplescreenstate.ui.model.ViewState
import com.uziassantosferreira.simplescreenstate.ui.throwable.GenericUiException
import com.uziassantosferreira.simplescreenstate.ui.throwable.NetworkUiException

fun Throwable.getUiThrowable(): ViewState.Failure<Throwable> =
    when (this) {
        is NetworkException -> {
            ViewState.Failure(NetworkUiException())
        }
        else -> ViewState.Failure(GenericUiException())
    }