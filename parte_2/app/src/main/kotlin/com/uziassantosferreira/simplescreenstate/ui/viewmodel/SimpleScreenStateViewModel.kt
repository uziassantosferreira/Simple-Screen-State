package com.uziassantosferreira.simplescreenstate.ui.viewmodel;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uziassantosferreira.simplescreenstate.ui.model.ViewState
import com.uziassantosferreira.simplescreenstate.coordination.SimpleScreenStateCoordinationSingle
import com.uziassantosferreira.simplescreenstate.ui.throwable.NetworkUiException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SimpleScreenStateViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    private val viewState: MutableLiveData<ViewState<Int, Throwable>> by lazy {
        MutableLiveData<ViewState<Int, Throwable>>()
    }

    fun getSomethingLiveData(): LiveData<ViewState<Int, Throwable>> {
        return viewState
    }

    fun executeSomething() {
        disposable.add(getSomething()
            .subscribe({
                viewState.value = ViewState.Success(it)
            }, {
                print(it)
            })
        )
    }

    private fun getSomething(): Single<Int> {
        return Single
            .create<Int> { emitter ->
                emitter.onSuccess(1)
            }
            .delay(3, TimeUnit.SECONDS, true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(SimpleScreenStateCoordinationSingle(viewState))
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
