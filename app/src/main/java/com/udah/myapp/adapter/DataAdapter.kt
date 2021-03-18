package com.udah.myapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udah.myapp.R
import com.udah.myapp.models.getdata.DataItem
import kotlinx.android.synthetic.main.card_app.view.*


class DataAdapter(var data: List<DataItem>?,val itemClick:OnClickListener) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama = itemView.nama
        val harga = itemView.harga
        val satuan = itemView.satuan
        val total = itemView.total
        val delete = itemView.delete
        val update =itemView.update

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_app,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size?:0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.nama.text = item?.nama
        holder.harga.text = item?.harga
        holder.satuan.text = item?.satuan
        holder.total.text = item?.total

        holder.itemView.setOnClickListener {
            itemClick.detail(item)
        }
        holder.delete.setOnClickListener {
            itemClick.delete(item)
        }
        holder.update.setOnClickListener {
            itemClick.update(item)
        }

    }
    interface OnClickListener {
        fun detail(item: DataItem?)
        fun delete(item: DataItem?)
        fun update(item:DataItem?)
    }


}
