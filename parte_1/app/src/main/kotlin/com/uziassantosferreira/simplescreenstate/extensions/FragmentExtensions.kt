package com.uziassantosferreira.simplescreenstate.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.uziassantosferreira.simplescreenstate.ui.model.SimpleScreenState
import com.uziassantosferreira.simplescreenstate.feedback.Feedback

fun Fragment.showFeedback(feedback: Feedback, @IdRes feedbackIdContainer: Int) {
    view?.findViewById<View>(feedbackIdContainer)?.hidden = false
    childFragmentManager.showFeedback(feedback, feedbackIdContainer)
}

fun Fragment.showServerError(@IdRes feedbackIdContainer: Int) {
    showFeedback(Feedback.serverError(requireContext()), feedbackIdContainer)
}

fun Fragment.showNoInternetError(@IdRes feedbackIdContainer: Int) {
    showFeedback(Feedback.noInternet(requireContext()), feedbackIdContainer)

}

fun Fragment.observeSimpleScreenState(simpleScreenState: LiveData<SimpleScreenState>,
                                      @IdRes loadingIdContainer: Int,
                                      @IdRes feedbackIdContainer: Int) {

    simpleScreenState.observe(this, Observer { viewState ->
        when (viewState) {
            SimpleScreenState.Loading -> {
                view?.findViewById<View>(loadingIdContainer)?.hidden = false
                view?.findViewById<View>(feedbackIdContainer)?.hidden = true
            }
            SimpleScreenState.GenericError -> {
                view?.findViewById<View>(loadingIdContainer)?.hidden = true
                showServerError(feedbackIdContainer)
            }
            SimpleScreenState.NetworkError -> {
                view?.findViewById<View>(loadingIdContainer)?.hidden = true
                showNoInternetError(feedbackIdContainer)
            }
            else -> {
                view?.findViewById<View>(loadingIdContainer)?.hidden = true
                view?.findViewById<View>(feedbackIdContainer)?.hidden = true
            }
        }
    })
}