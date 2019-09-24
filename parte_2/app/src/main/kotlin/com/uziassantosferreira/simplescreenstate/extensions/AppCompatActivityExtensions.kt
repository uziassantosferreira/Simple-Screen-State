package com.uziassantosferreira.simplescreenstate.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.uziassantosferreira.simplescreenstate.ui.model.ViewState
import com.uziassantosferreira.simplescreenstate.feedback.Feedback
import com.uziassantosferreira.simplescreenstate.ui.throwable.NetworkUiException

fun AppCompatActivity.showFeedback(feedback: Feedback, @IdRes feedbackIdContainer: Int) {
    findViewById<View>(feedbackIdContainer).hidden = false
    supportFragmentManager.showFeedback(feedback, feedbackIdContainer)
}

fun AppCompatActivity.showServerError(@IdRes feedbackIdContainer: Int) {
    showFeedback(Feedback.serverError(this), feedbackIdContainer)
}

fun AppCompatActivity.showNoInternetError(@IdRes feedbackIdContainer: Int) {
    showFeedback(Feedback.noInternet(this), feedbackIdContainer)
}

fun <Result> AppCompatActivity.observeSimpleScreenState(
    liveData: LiveData<ViewState<Result, Throwable>>,
    @IdRes loadingIdContainer: Int,
    @IdRes feedbackIdContainer: Int,
    success: (Result) -> Unit
) {
    liveData.observe(this, Observer { viewState ->
        when (viewState) {
            is ViewState.Success -> {
                findViewById<View>(loadingIdContainer).hidden = true
                findViewById<View>(feedbackIdContainer).hidden = true
                success.invoke(viewState.result)
            }
            is ViewState.Loading -> {
                findViewById<View>(loadingIdContainer).hidden = false
                findViewById<View>(feedbackIdContainer).hidden = true
            }
            is ViewState.Failure -> {
                findViewById<View>(loadingIdContainer).hidden = true
                if (viewState.error is NetworkUiException) {
                    showNoInternetError(feedbackIdContainer)
                } else {
                    showServerError(feedbackIdContainer)
                }
            }
        }
    })
}