package com.uziassantosferreira.simplescreenstate.feedback

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import com.uziassantosferreira.simplescreenstate.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feedback(
    val title: String,
    val description: CharSequence
) : Parcelable {

    companion object {
        @JvmStatic
        fun noInternet(context: Context): Feedback =
            Feedback(
                title = context.getString(R.string.feedback_no_internet_title),
                description = context.getString(R.string.feedback_no_internet_description)
            )

        @JvmStatic
        fun serverError(context: Context): Feedback =
            Feedback(
                title = context.getString(R.string.feedback_generic_error_title),
                description = context.getString(R.string.feedback_generic_error_description)
            )
    }
}


@Parcelize
data class FeedbackRes(
    @StringRes val title: Int,
    @StringRes val description: Int
) : Parcelable
