package com.uziassantosferreira.simplescreenstate.ui.model

sealed class SimpleScreenState {
    object Loading : SimpleScreenState()
    object GenericError : SimpleScreenState()
    object NetworkError : SimpleScreenState()
}