package com.example.appleproject.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appleproject.data.Item
import com.example.appleproject.databinding.RvItemBinding
import java.text.DecimalFormat

class MainAdapter (val itemList: MutableList<Item>) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }
    var itemLongClick : ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MainAdapter.MainHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.image.setImageResource(itemList[position].image)
        holder.title.text = itemList[position].title.toString()
        holder.address.text = itemList[position].address.toString()
        holder.price.text = DecimalFormat("###,###원").format(itemList[position].price).toString()
        holder.like.text = itemList[position].like.toString()
        holder.chat.text = itemList[position].chat.toString()


        holder.itemView.setOnLongClickListener {
            val longClick = android.os.Handler()
                longClick.postDelayed({
                    itemLongClick?.onLongClick(it, position)
                }, 500)
            return@setOnLongClickListener(true)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemCount(): Int {
        return itemList.size
    }


    inner class MainHolder(val binding : RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imgItem
        val title = binding.textItemTitle
        val address = binding.textItemAddress
        val price = binding.textItemPrice
        val like = binding.textItemLike
        val chat = binding.textItemChat
    }
}