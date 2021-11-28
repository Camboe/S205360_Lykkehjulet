package com.example.s205360lykkehjulet.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.s205360lykkehjulet.R
import com.example.s205360lykkehjulet.databinding.ActivityMainBinding
import com.example.s205360lykkehjulet.databinding.FragmentGameBinding

class GameFrag : Fragment() {
    // private val points_at_start
    // lav en liste af bogstaver.
    // lav en liste af ord.



    //reference til GameViewHolder
    private val viewModel : GameViewHolder by viewModels ()

    // gives access to view in fragment_game
    private lateinit var binding: FragmentGameBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false)


        // random choose word:
        val category = context?.resources?.getString(requireArguments().getInt("Title"))

        viewModel.generateWord(category!!)


        binding.textView.text = viewModel.newWord().toString()
        return binding.root
    }

        //Click on buttons
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.kobVokal.setOnClickListener {

        }
        binding.spinArrow.setOnClickListener{ spinWheel() }
        binding.getOrd.setOnClickListener{ }

    }

    fun spinWheel(){
        Toast.makeText(activity, "spin igen", Toast.LENGTH_SHORT).show()

    }

    fun guessWord () {

    }

    fun buyVocal () {

    }
    //private fun submitLetters() {
        //val letter = binding.textFieldWord.text.toString()
        //if (viewModel.checkGuess(letter)) {


        //}
    //}


}