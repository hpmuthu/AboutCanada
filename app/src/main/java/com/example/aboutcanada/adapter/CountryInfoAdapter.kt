package com.example.aboutcanada.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aboutcanada.R
import com.example.aboutcanada.pojo.CountryInfoItem

class CountryInfoAdapter(val mContext: Context, private val countryInfoList: List<CountryInfoItem>) :
    RecyclerView.Adapter<CountryInfoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_country, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryInfoList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val countryInfoItem = countryInfoList.get(position)

        holder.tv_title.text = countryInfoItem.title ?: "-"
        holder.tv_desc.text = countryInfoItem.description ?: "-"

        if(countryInfoItem.imageHref != null && countryInfoItem.imageHref.isNotEmpty()) {
            Glide
                .with(holder.iv_img.context)
                .load(countryInfoItem.imageHref)
                .centerCrop()
                .placeholder(R.drawable.no_image)
                .into(holder.iv_img)
        } else {
            holder.iv_img.setImageResource(R.drawable.no_image)
        }

    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val ll_main = itemView.findViewById(R.id.ll_main) as LinearLayout
        val tv_title = itemView.findViewById(R.id.tv_title) as TextView
        val tv_desc = itemView.findViewById(R.id.tv_desc) as TextView
        val iv_img = itemView.findViewById(R.id.iv_img) as ImageView
    }
}