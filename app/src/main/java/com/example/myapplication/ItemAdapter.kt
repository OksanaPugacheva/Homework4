package com.example.myapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ListItemBinding
import com.google.android.material.snackbar.Snackbar


class ItemAdapter(val context: Context, val items: ArrayList<DataModel>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val titleBaseLineOffset = context.dpToPx(32)
    private val heightOneLine = context.dpToPx(56)
    private val heightTwoLine = context.dpToPx(64)

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }


    private inner class View1ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.title)
        var SubTitle: TextView = itemView.findViewById(R.id.subtitle)
        var Lead: ImageView = itemView.findViewById(R.id.leading)
        fun bind(position: Int) {
            val recyclerViewModel = items[position]
            title.text = recyclerViewModel.itemName
            SubTitle.text = recyclerViewModel.subti
            Lead.setImageResource(recyclerViewModel.image)
        }

    }


    private inner class View2ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var anotherTitle: TextView = itemView.findViewById(R.id.another_title)
        var anotherSubTitle: TextView = itemView.findViewById(R.id.another_subtitle)
        var anotherLead: ImageView = itemView.findViewById(R.id.another_leading)
        fun bind(position: Int) {

            val recyclerViewModel = items[position]
            anotherTitle.text = recyclerViewModel.itemName
            anotherSubTitle.text = recyclerViewModel.subti
            anotherLead.setImageResource(recyclerViewModel.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
            )
        }
        return View2ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.another_list_item, parent, false)
        )
    }


    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            var snacText = items[position].itemName
            Snackbar.make(it, "$snacText", Snackbar.LENGTH_SHORT).show()
        }
        if (items[position].viewType === VIEW_TYPE_ONE) {
            (holder as View1ViewHolder).bind(position)

        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }

}


