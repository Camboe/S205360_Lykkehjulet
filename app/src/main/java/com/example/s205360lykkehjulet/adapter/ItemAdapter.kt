package com.example.s205360lykkehjulet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.s205360lykkehjulet.R
import com.example.s205360lykkehjulet.model.CategoriesButton


class ItemAdapter (private val context: Context, private val dataset: List<CategoriesButton>)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()
{

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.categorieTitle)
        val imageView: ImageView = view.findViewById(R.id.categorieImage)
        val button: Button = view.findViewById(R.id.categorieButton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //Create a new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
        holder.button.setOnClickListener {
            Toast.makeText(context, "test" + context.resources.getString(item.stringResourceId), Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int {
        return dataset.size

    }

}
