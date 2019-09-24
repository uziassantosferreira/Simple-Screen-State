package com.uziassantosferreira.simplescreenstate.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uziassantosferreira.simplescreenstate.R
import kotlinx.android.synthetic.main.fragment_feedback.*

class FeedbackFragment : Fragment() {

    private val feedback by lazy { arguments!!.getParcelable<Feedback>(FEEDBACK)!! }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadScreen()
    }

    private fun loadScreen() {
        feedbackTitle.text = feedback.title
        feedbackDescription.text = feedback.description
    }

    companion object {
        private const val FEEDBACK = "Feedback"

        fun newInstance(feedback: Feedback): FeedbackFragment {
            val fragment = FeedbackFragment()
            fragment.arguments = createBundle(feedback)
            return fragment
        }

        private fun createBundle(feedback: Feedback): Bundle =
            Bundle().apply {
                putParcelable(FEEDBACK, feedback)
            }
    }
}
