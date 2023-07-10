package com.example.fitpeotasksample.ui.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeotasksample.R
import com.example.fitpeotasksample.data.model.GetServerData
import com.example.fitpeotasksample.databinding.ListItemBinding
import com.example.fitpeotasksample.ui.detail.activity.DetailActivity
import com.example.fitpeotasksample.ui.home.activity.HomeActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ListDataAdapter(private val listData: ArrayList<GetServerData>,private val context: Context) : RecyclerView.Adapter<ListDataAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    // inner class
    inner class MyViewHolder(private val listItemBinding: ListItemBinding) : RecyclerView.ViewHolder(listItemBinding.root) {

        @OptIn(ExperimentalCoroutinesApi::class)
        @SuppressLint("NotifyDataSetChanged")
        fun bindItems(getDataResponse: GetServerData) {

            listItemBinding.itemData=getDataResponse

            listItemBinding.btnDetail.setOnClickListener {
                HomeActivity.selectedData=getDataResponse
                context.startActivity(Intent(context, DetailActivity::class.java))
            }
        }

    }

}