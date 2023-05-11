package com.jorfald.friender.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.jorfald.friender.model.dataClasses.User
import com.jorfald.friender.R
import com.jorfald.friender.Utils
import com.squareup.picasso.Picasso

class FriendsView(context: Context) : ConstraintLayout(context) {
    private val view: View = LayoutInflater.from(context).inflate(R.layout.friends_view, this)

    private val friendProfilePicture: ImageView = view.findViewById(R.id.friend_profile_picture)
    private val friendClickWrapper: ConstraintLayout = view.findViewById(R.id.friend_click_wrapper)
    private val friendLocationText: TextView = view.findViewById(R.id.friend_place_text)
    private val friendDeleteButton: ImageView = view.findViewById(R.id.friend_cross_button)
    private val friendAgeTextView: TextView = view.findViewById(R.id.friend_age_text)
    private val friendNameText: TextView = view.findViewById(R.id.friend_name_text)

    @SuppressLint("SetTextI18n")
    fun setData(user: User) {
        friendNameText.text = Utils.getFullName(user.first_name, user.last_name)
        friendAgeTextView.text = "(${Utils.getAge(2022, user.date_of_birth)})"
        friendLocationText.text = Utils.getLocationText(user.address)

        friendClickWrapper.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("More info")
                .setMessage(
                    Utils.getEmploymentText(user.employment) +
                            "\n${context.getString(R.string.email)}: ${user.email}"
                )
                .setPositiveButton(
                    android.R.string.ok
                ) { _, _ ->
                }
                .show()
        }
        Picasso.get().load(
            user.imageUrl
        ).into(friendProfilePicture)
    }

    fun setCrossListener(click: () -> Unit) {
        friendDeleteButton.setOnClickListener {
            click()
        }
    }
}
