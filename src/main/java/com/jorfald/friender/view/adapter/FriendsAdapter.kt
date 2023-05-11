package com.jorfald.friender.view.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.friender.model.dataClasses.User
import com.jorfald.friender.view.FriendsView

class FriendsAdapter(
    var dataSet: List<User>,
    private val crossClicked: ((User) -> Unit)? = null
) :
    RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {

    inner class FriendsViewHolder(val view: FriendsView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val view = FriendsView(parent.context)

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return FriendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.view.setData(dataSet.reversed()[position])
        holder.view.setCrossListener {
            crossClicked?.let { it(dataSet[position]) }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<User>) {
        dataSet = newList
        notifyDataSetChanged()
    }

}
