package com.example.s205360lykkehjulet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s205360lykkehjulet.R

// Inspiration from unit 2 Affirmation app
// https://developer.android.com/codelabs/basic-android-kotlin-training-recyclerview-scrollable-list#4
class RulesAdapter(private val ruleList: List<String>) :
    RecyclerView.Adapter<RulesAdapter.RulesViewHolder>() {
    class RulesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val ruleText: TextView = view.findViewById(R.id.text_rules)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_rules, parent, false)
        return RulesViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        holder.ruleText.text = ruleList[position]

    }

    override fun getItemCount(): Int {
        return ruleList.size

    }

}