package com.uziassantosferreira.simplescreenstate.ui.model

sealed class ViewState<out Result, out Error : Throwable> {
    object Loading : ViewState<Nothing, Nothing>()
    data class Success<out R>(val result: R) : ViewState<R, Nothing>()
    data class Failure<out E : Throwable>(val error: E) : ViewState<Nothing, E>()
}