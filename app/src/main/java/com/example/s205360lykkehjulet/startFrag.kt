package com.example.s205360lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.s205360lykkehjulet.Data.DataCategories
import com.example.s205360lykkehjulet.adapter.ItemAdapter


class startFrag : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_start, container, false)

        val myDataset = DataCategories().loadButtonContext()
        val recyclerView = root?.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView!!.adapter = ItemAdapter(requireContext(), myDataset)
        recyclerView.setHasFixedSize(true)

        return root


    }

}