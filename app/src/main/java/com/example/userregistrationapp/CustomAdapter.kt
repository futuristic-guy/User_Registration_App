package com.example.userregistrationapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CustomAdapter(var context: Context,var uList: ArrayList<Model>) : RecyclerView.Adapter<ViewHolder>() {

    lateinit var listener : OnItemClickListener


    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        listener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var inflater = LayoutInflater.from(context)
       var view = inflater.inflate(R.layout.recycle_view_item,parent, false)
       return ViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(uList[position].gender.toString() == "Male"){
            holder.userImage.setImageResource(R.drawable.male)
        }else{
            holder.userImage.setImageResource(R.drawable.female)
        }

        holder.userName.text = uList[position].userName
        holder.userEmail.text = uList[position].email
        holder.userPhone.text = "+91 "+ uList[position].phone
    }


    override fun getItemCount(): Int {
        return uList.size
    }

}

interface OnItemClickListener {
    fun onClick(position: Int)
}


class ViewHolder(itemView: View,mListener : OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
    var userImage = itemView.findViewById<ImageView>(R.id.item_image)
    var userName = itemView.findViewById<TextView>(R.id.item_name)
    var userEmail = itemView.findViewById<TextView>(R.id.item_email)
    var userPhone = itemView.findViewById<TextView>(R.id.item_phone)

   init {
       itemView.setOnClickListener {
           mListener.onClick(adapterPosition)
       }
   }

}