package com.example.s205360lykkehjulet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.s205360lykkehjulet.R
import com.example.s205360lykkehjulet.model.CategoriesButton

/**
 * Inspiration from unit 2 Affirmation app
 * https://developer.android.com/codelabs/basic-android-kotlin-training-recyclerview-scrollable-list#4
 */
class CategoryAdapter(private val context: Context, private val dataset: List<CategoriesButton>) :
    RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.categorieTitle)
        val imageView: ImageView = view.findViewById(R.id.categorieImage)
        val button: Button = view.findViewById(R.id.categorieButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //Create a new view
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_category, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)

        holder.button.setOnClickListener {
            val bundle = bundleOf("Title" to item.stringResourceId)
            Navigation.findNavController(it).navigate(R.id.action_startFrag_to_gameFrag, bundle)


        }
    }

    override fun getItemCount(): Int {
        return dataset.size

    }

}
