package com.uziassantosferreira.simplescreenstate.ui.viewmodel;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uziassantosferreira.simplescreenstate.ui.model.SimpleScreenState
import com.uziassantosferreira.simplescreenstate.coordination.SimpleScreenStateCoordinationSingle
import com.uziassantosferreira.simplescreenstate.throwable.NetworkException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SimpleScreenStateViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    private val screenState: MutableLiveData<SimpleScreenState> by lazy {
        MutableLiveData<SimpleScreenState>().also {
            //            executeSomething()
        }
    }

    fun getSomethingLiveData(): LiveData<SimpleScreenState> {
        return screenState
    }

    fun executeSomething() {
        disposable.add(getSomething()
            .subscribe({
            print(it)
        }, {
            print(it)
        }))
    }

    private fun getSomething(): Single<Int> {
        return Single
            .create<Int> { emitter ->
//                emitter.onError(NetworkException())
                emitter.onSuccess(1)
            }
            .delay(3, TimeUnit.SECONDS, true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(SimpleScreenStateCoordinationSingle(screenState))
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
