package com.example.chatwise.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.chatwise.R
import com.example.chatwise.models.Product

class ChildAdapter(private val childList: List<Product>) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.product_img)
        val name: TextView= itemView.findViewById(R.id.product_name)
        val des: TextView = itemView.findViewById(R.id.product_des)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val product = childList[position]
        holder.name.text = product.title
        holder.des.text = product.description
        // Load image if available
        if (product.images.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(product.images[0])
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .apply(RequestOptions().transform(RoundedCorners(10)))
                .into(holder.image)
        } else {
            // Handle the case where no image is available
            //holder.image.setImageResource(R.drawable.placeholder_image)
        }

    }

    override fun getItemCount(): Int {
        return childList.size
    }

}