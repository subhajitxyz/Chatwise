package com.example.chatwise.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwise.R
import com.example.chatwise.models.ProductsModel

class ParentAdapter(private val parentList: List<ProductsModel>) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTv: TextView = itemView.findViewById(R.id.parentTitleTv)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.langRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_item, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val parentItem = parentList[position]

        holder.titleTv.text = "Product List "+(position+1).toString()

        holder.childRecyclerView.setHasFixedSize(true)
        holder.childRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
       val adapter = ChildAdapter(parentItem.products)
        holder.childRecyclerView.adapter = adapter
    }

    override fun getItemCount(): Int {
        return parentList.size
    }
}