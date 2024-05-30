package com.example.practicaltestpythonserver.mvvm.activity

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltestpythonserver.databinding.ListItemsOfScreenBinding
import com.example.practicaltestpythonserver.mvvm.dataInterface.MovieItemClickListener
import com.example.practicaltestpythonserver.mvvm.dataModel.screenData


class ScreenListAdapter(
    val context: Context,
    val arrayList: ArrayList<screenData>,
    private var screensItemClickListener: MovieItemClickListener? = null
) : RecyclerView.Adapter<ScreenListAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        Log.d("userProfile->", "ADAPTER CALL")

        val binding = ListItemsOfScreenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val screenData = arrayList[position]

        holder.binding.totalSeat.text = screenData.total_seats.toString()
        holder.binding.txtScreenName.text = screenData.name
        Log.d("userProfile res->", "profileImageCrop: ${screenData.toString()}")

        holder.itemView.setOnClickListener {
            screensItemClickListener?.movieItemClickListener(position)
        }

    }


    /**
     * Get Item Counts
     */
    override fun getItemCount(): Int {
        return arrayList.size
    }

    /**
     * Custom FavouriteAdsViewHolder
     */
    class UserViewHolder(val binding: ListItemsOfScreenBinding) :
        RecyclerView.ViewHolder(binding.root)


}