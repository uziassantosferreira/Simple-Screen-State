package com.uziassantosferreira.simplescreenstate.coordination

import androidx.lifecycle.MutableLiveData
import com.uziassantosferreira.simplescreenstate.extensions.getUiThrowable
import com.uziassantosferreira.simplescreenstate.ui.model.ViewState
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class SimpleScreenStateCoordinationSingle<Result>
    (private val viewState: MutableLiveData<ViewState<Result, Throwable>>) :
    SingleTransformer<Result, Result> {
    override fun apply(upstream: Single<Result>): SingleSource<Result> {
        return upstream.doOnSubscribe {
            viewState.value = ViewState.Loading
        }
            .doOnSuccess {
                viewState.value = ViewState.Success(it)
            }
            .doOnError {
                viewState.value = it.getUiThrowable()
            }
    }
}