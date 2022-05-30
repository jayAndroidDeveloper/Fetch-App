package com.example.fetch.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fetch.Model.DataResponse
import com.example.fetch.R
import kotlin.math.log

class DataAdapter(private var dataList: List<DataResponse>, private val context: Context) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.data_recycler, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList.get(position)
        holder.title.text = dataModel.title
        holder.desc.text = dataModel.description
        holder.earn.text = dataModel.earning

    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {

        var desc: TextView


        var title: TextView


        var logo: ImageView

        var earn: TextView

        init {
            desc = itemLayoutView.findViewById(R.id.desc)
            title = itemLayoutView.findViewById(R.id.title)
            logo = itemLayoutView.findViewById(R.id.logo)
            earn = itemLayoutView.findViewById(R.id.earn)

        }
    }

    fun filterList(filteredNames: ArrayList<DataResponse>) {
        Log.e("list", filteredNames.toString())
        Log.e("list", filteredNames.size.toString())
        // this.dataList.clear()
        this.dataList = filteredNames
        notifyDataSetChanged()
    }
}