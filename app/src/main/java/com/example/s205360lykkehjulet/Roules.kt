package com.example.s205360lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.s205360lykkehjulet.databinding.FragmentRoulesBinding


class Roules : Fragment() {
    private lateinit var binding: FragmentRoulesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRoulesBinding.inflate(inflater, container, false)

        return binding.root
    }
    //Set click on button startSpil
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startSpil.setOnClickListener{goToFragment()
        }


    }
    //Method that moves on to the next fragment.
    fun goToFragment(){
        findNavController().navigate(R.id.action_roules_to_startFrag)
    }
}

