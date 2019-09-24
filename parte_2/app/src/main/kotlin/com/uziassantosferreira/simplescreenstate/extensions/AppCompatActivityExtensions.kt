package com.uziassantosferreira.simplescreenstate.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.uziassantosferreira.simplescreenstate.ui.model.SimpleScreenState
import com.uziassantosferreira.simplescreenstate.feedback.Feedback

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

fun AppCompatActivity.observeSimpleScreenState(
    simpleScreenState: LiveData<SimpleScreenState>,
    @IdRes loadingIdContainer: Int,
    @IdRes feedbackIdContainer: Int
) {
    simpleScreenState.observe(this, Observer { viewState ->
        when (viewState) {
            SimpleScreenState.Loading -> {
                findViewById<View>(loadingIdContainer).hidden = false
                findViewById<View>(feedbackIdContainer).hidden = true
            }
            SimpleScreenState.GenericError -> {
                findViewById<View>(loadingIdContainer).hidden = true
                showServerError(feedbackIdContainer)
            }
            SimpleScreenState.NetworkError -> {
                findViewById<View>(loadingIdContainer).hidden = true
                showNoInternetError(feedbackIdContainer)
            }
            else -> {
                findViewById<View>(loadingIdContainer).hidden = true
                findViewById<View>(feedbackIdContainer).hidden = true
            }
        }
    })
}