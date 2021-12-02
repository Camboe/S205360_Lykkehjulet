package com.example.s205360lykkehjulet.game

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.s205360lykkehjulet.R
import com.example.s205360lykkehjulet.databinding.FragmentGameBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameFrag : Fragment() {
    //reference til GameViewHolder
    private val viewModel: GameViewHolder by viewModels()

    // gives access to view in fragment_game
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    // Click on buttons spinArrow and getBogstav
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category = context?.resources?.getString(requireArguments().getInt("Title"))

        viewModel.generateWord(category!!)

        // updates point and life for the player to see.
        binding.point.text = "PONIT: ${viewModel.point}"
        binding.life.text = "LIV: ${viewModel.life}"

        // random choose word
        binding.wordToGuessText.text = viewModel.generateWord(category)

        // adding the category name from stat_frag to game_frag
        binding.categorieView.text = category


        binding.spinArrow.setOnClickListener {
            spinWheel()
            fallitCheck(view)
            binding.textFieldWord.isEnabled = true
            binding.textFieldWord.showkeyboard()
        }
        binding.spinArrow.setOnClickListener{ spinWheel() }
        binding.getOrd.setOnClickListener{ }

    }

    fun spinWheel(){
        Toast.makeText(activity, "spin igen", Toast.LENGTH_SHORT).show()

    }

    fun guessWord () {

    }


    // Checks if the player landed on fallit, then call dialog.
    private fun fallitCheck(view: View){
        if (viewModel.field == "FALLIT") {
            viewModel.fallit()
            showFinalDialog(view)
        }

    }


    // Check if the letters is right.
    private fun submitLetters(view: View) {
        val letter = binding.textFieldWord.text.toString()
        viewModel.checkGuess(letter)
        if (viewModel.winGame()) {
            showFinalDialog(view)
        }
        fallitCheck(view)

    }
    //private fun submitLetters() {
        //val letter = binding.textFieldWord.text.toString()
        //if (viewModel.checkGuess(letter)) {


        //}
    //}


}