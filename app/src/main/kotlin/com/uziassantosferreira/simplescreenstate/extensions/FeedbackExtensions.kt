package com.uziassantosferreira.simplescreenstate.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.uziassantosferreira.simplescreenstate.feedback.Feedback
import com.uziassantosferreira.simplescreenstate.feedback.FeedbackFragment

fun FragmentManager.showFeedback(feedback: Feedback, @IdRes feedbackIdContainer: Int) {
    val fragment = FeedbackFragment.newInstance(feedback)
    replace(feedbackIdContainer, fragment)
}

fun FragmentManager.replace(
    id: Int,
    fragment: Fragment,
    tag: String? = null
) {
    val fragmentTransaction = this.beginTransaction()
    fragmentTransaction.replace(id, fragment, tag)
    fragmentTransaction.commit()
}
