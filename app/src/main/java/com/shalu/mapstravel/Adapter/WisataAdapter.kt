package com.shalu.mapstravel.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shalu.mapstravel.R
import com.shalu.mapstravel.DetailWisataActivity
import com.shalu.mapstravel.Model.ModelWisata

class WisataAdapter(
    private var context: Context,
    private val wisataList: List<ModelWisata>
) : RecyclerView.Adapter<WisataAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgWisata2)
        val nama: TextView = itemView.findViewById(R.id.wisata2)
        val lokasi: TextView = itemView.findViewById(R.id.lokasi2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val nView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wisata, parent, false)
        return MyViewHolder(nView)
    }

    override fun getItemCount(): Int {
        return wisataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = wisataList[position]
        holder.img.setImageResource(currentItem.Img)
        holder.nama.text = currentItem.nama
        holder.lokasi.text = currentItem.lokasi

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailWisataActivity::class.java)
            intent.putExtra("Img", currentItem.Img)
            intent.putExtra("nama", currentItem.nama)
            intent.putExtra("lokasi", currentItem.lokasi)
            intent.putExtra("des", currentItem.des)
            intent.putExtra("lat", currentItem.latitude) // Passing latitude
            intent.putExtra("lng", currentItem.longitude) // Passing longitude

            context.startActivity(intent)
        }
    }
}