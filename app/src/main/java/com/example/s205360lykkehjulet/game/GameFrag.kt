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

        // hides the keyboard, invoke you to spin on the spinButton before you can add letters.
        binding.textFieldWord.isEnabled = false

        binding.getBogstav.setOnClickListener { submitLetters(view)
        binding.wordToGuessText.text = viewModel.checkWord(binding.wordToGuessText.text as String, binding.textFieldWord.text.toString().single())}

        // hides the keyboard when player keys input. Inspiration from Stack overflow.
        val hideKeyboard = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.textFieldWord.addTextChangedListener {
            hideKeyboard.hideSoftInputFromWindow(
                view.windowToken,
                0
            )
        }

    }

    fun spinWheel(){
        Toast.makeText(activity, "spin igen", Toast.LENGTH_SHORT).show()

    }
    // Shows the keybord for the player.
    // It's called in line 61 when the player use the spin button the keyboard comes up.
    private fun View.showkeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
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

    // Inspiration from unit 3 Uscramble app. Sets the messages on the dialogboks, and shows the point.
    private fun showFinalDialog(view: View) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.endGame))
            .setMessage(getString(R.string.your_point, viewModel.point))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ ->
                exitGame(view)
            }
            .setPositiveButton(getString(R.string.play_again)) { _, _ ->
                restartGame()
            }
            .show()
    }

        //}
    //}


}