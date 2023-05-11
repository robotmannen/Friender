package com.jorfald.friender.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.jorfald.friender.R
import com.jorfald.friender.model.dataClasses.User
import com.jorfald.friender.Utils
import com.jorfald.friender.viewModel.FriendsViewModel
import com.squareup.picasso.Picasso
import java.util.*


class MainFragment : Fragment() {
    private val viewModel: FriendsViewModel by activityViewModels()

    lateinit var queue: RequestQueue

    private lateinit var myFriendsButton: Button
    private lateinit var occupationText: TextView
    private lateinit var locationText: TextView
    private lateinit var profileImage: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var crossButton: ImageView
    private lateinit var checkButton: ImageView
    private lateinit var genderIcon: ImageView
    private lateinit var nameText: TextView
    private lateinit var ageText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews(view)
        bindObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        queue = Volley.newRequestQueue(activity)
        viewModel.fetchRandomUser(queue)
    }

    private fun bindViews(view: View) {
        myFriendsButton = view.findViewById(R.id.my_friends_button)
        occupationText = view.findViewById(R.id.occupation_text)
        locationText = view.findViewById(R.id.location_text)
        profileImage = view.findViewById(R.id.profile_image)
        crossButton = view.findViewById(R.id.cross_button)
        checkButton = view.findViewById(R.id.check_button)
        progressBar = view.findViewById(R.id.loader)
        genderIcon = view.findViewById(R.id.gender_icon)
        nameText = view.findViewById(R.id.name_text)
        ageText = view.findViewById(R.id.age_text)
    }

    private fun bindObservers() {
        viewModel.randomUser.observe(viewLifecycleOwner) { user ->
            progressBar.visibility = View.GONE
            displayUserInfo(user)
            onClick(user)
        }
    }

    private fun onClick(user: User) {
        checkButton.setOnClickListener {
            viewModel.insertUser(user)
            viewModel.fetchRandomUser(queue)
        }
        crossButton.setOnClickListener {
            viewModel.fetchRandomUser(queue)
        }
        myFriendsButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToLikedFriendsFragment())
        }
    }

    //Changes the information of the views to the newly fetched data
    private fun displayUserInfo(user: User) {
        occupationText.text = Utils.getEmploymentText(user.employment)
        locationText.text = Utils.getLocationText(user.address)
        nameText.text = Utils.getFullName(user.first_name, user.last_name)
        ageText.text = Utils.getAge(
            Calendar.getInstance().get(Calendar.YEAR), user.date_of_birth
        )
        genderIcon.setImageDrawable(getGenderIcon(requireContext(), user.gender))
        ageText.setTextColor(
            if (Utils.isMale(user.gender)) {
                ContextCompat.getColor(requireContext(), R.color.blue)
            } else {
                ContextCompat.getColor(requireContext(), R.color.pink)
            }
        )
        Picasso.get().load(
            user.imageUrl
        ).into(profileImage)
        progressBar.visibility = View.GONE
    }

    private fun getGenderIcon(context: Context, gender: String): Drawable? {
        return if (Utils.isMale(gender)) {
            ContextCompat.getDrawable(context, R.drawable.ic_male)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_female)
        }
    }
}