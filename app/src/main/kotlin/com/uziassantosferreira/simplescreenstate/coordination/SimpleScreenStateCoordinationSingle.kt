package com.uziassantosferreira.simplescreenstate.coordination

import androidx.lifecycle.MutableLiveData
import com.uziassantosferreira.simplescreenstate.extensions.getScreenState
import com.uziassantosferreira.simplescreenstate.ui.model.SimpleScreenState
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class SimpleScreenStateCoordinationSingle<T>(private val simpleScreenState: MutableLiveData<SimpleScreenState>) :
    SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.doOnSubscribe {
            simpleScreenState.value = SimpleScreenState.Loading
        }
            .doOnSuccess {
                simpleScreenState.value = null
            }
            .doOnError {
                simpleScreenState.value = it.getScreenState()
            }
    }
}