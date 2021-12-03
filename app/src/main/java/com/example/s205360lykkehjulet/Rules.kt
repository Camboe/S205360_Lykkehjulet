package com.example.s205360lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s205360lykkehjulet.adapter.RulesAdapter
import com.example.s205360lykkehjulet.databinding.FragmentRulesBinding

/**
 * Inspiration from unit 2 Affirmation app
 * https://developer.android.com/codelabs/basic-android-kotlin-training-recyclerview-scrollable-list#4
 */
class Rules : Fragment() {
    private lateinit var binding: FragmentRulesBinding

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRulesBinding.inflate(inflater, container, false)

        return binding.root
    }

    // takes the list of strings into the recyclerview.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf<String>(
            getString(R.string.rule_1),
            getString(R.string.rule_2),
            getString(R.string.rule_3),
            getString(R.string.rule_4),
            getString(R.string.rule_5),
            getString(R.string.rule_6),
            getString(R.string.rule_7),
            getString(R.string.rule_8),
            getString(R.string.rule_9),
            getString(R.string.rule_10),
            getString(R.string.rule_11),
            getString(R.string.rule_12),
            getString(R.string.rule_13),
            getString(R.string.rule_14),
            getString(R.string.rule_15)
        )
        val recyclerView = binding.rulesText
        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = RulesAdapter(list)
        }
        // Set click on button startSpil
        binding.startGame.setOnClickListener {
            goToFragment()
        }


    }


    /** inspiration from unit 3 CupCake app
    //https://developer.android.com/codelabs/basic-android-kotlin-training-shared-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-4%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-shared-viewmodel#2
     */
    // Method that moves on to the next fragment.
    fun goToFragment() {
        findNavController().navigate(R.id.action_roules_to_startFrag)
    }


}

