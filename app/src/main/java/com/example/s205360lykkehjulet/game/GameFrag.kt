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
    /**
     * Reference til GameViewHolder. Inspiration from Unscramble app unit 3
     * https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-viewmodel#3
     * */
    private val viewModel: GameViewHolder by viewModels()

    // Binding gives access to view in fragment_game
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.life.text = "${viewModel.life}"

        // Random choose word.
        binding.wordToGuessText.text = viewModel.generateWord(category)

        // Adding the category name from category_frag to game_frag.
        binding.categorieView.text = category

        // Does so you can't push the guess letter button.
        binding.guessLetterBtn.isEnabled = false

        // When the player click on the spin arrow, the guessLetterBtn and the textfieldWord
        // gets activated. And the keyboard will be showed.
        // Calls the method spinWheel and fallitCheck.
        binding.spinArrow.setOnClickListener {
            spinWheel()
            fallitCheck(view)
            binding.textFieldWord.isEnabled = true
            binding.guessLetterBtn.isEnabled = true
            binding.textFieldWord.showkeyboard()
        }

        // Hides the keyboard, invoke you to spin on the spinButton before you can add letters.
        binding.textFieldWord.isEnabled = false

        binding.guessLetterBtn.setOnClickListener {
            submitLetters(view)
            binding.wordToGuessText.text = viewModel.checkWord(
                binding.wordToGuessText.text as String,
                binding.textFieldWord.text.toString().single()
            )
        }

        /**
         * Hides the keyboard when player keys input. Inspiration from Stack overflow.
         * https://stackoverflow.com/questions/57622282/how-to-hide-keyboard-when-dialog-is-open
         */
        val hideKeyboard =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.textFieldWord.addTextChangedListener {
            hideKeyboard.hideSoftInputFromWindow(
                view.windowToken,
                0
            )
        }

    }

    // Updates life and field.
    @SuppressLint("SetTextI18n")
    fun spinWheel() {
        binding.wheelField.text = viewModel.wheelField()
        binding.life.text =  "${viewModel.life}"
    }


    /**
     * Collaboration whit Thamara Chellakooty s205337
     */
    // Shows the keybord for the player.
    // It's called in line 61 when the player use the spin button the keyboard comes up.
    private fun View.showkeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }


    // Checks if the player landed on fallit, then call dialog if the player is fallit.
    private fun fallitCheck(view: View) {
        if (viewModel.field == "FALLIT") {
            viewModel.fallit()
            showDialogFallit(view)
        }

    }



    /**
     *  Collaboration whit Thamara Chellakooty s205337
     */
    // Check if the letters is right, and updates the point and life.
    @SuppressLint("SetTextI18n")
    private fun submitLetters(view: View) {
        val letter = binding.textFieldWord.text.toString()
        viewModel.checkGuess(letter)
        binding.point.text = "POINT: ${viewModel._point}"
        binding.life.text = "${viewModel.life}"
        if (viewModel.winGame()) {
            showFinalDialog(view)
        }
        fallitCheck(view)

    }

    /**
     * Inspiration from unit 3 Uscramble app. Sets the messages on the dialogboks, and shows the point.
     *  https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-viewmodel#7
    */
    private fun showFinalDialog(view: View) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.endGame))
            .setMessage(getString(R.string.your_point, viewModel.point))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ ->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)) { _, _ ->
                restartGame(view)
            }
            .show()
    }

    /**
     * Inspiration from unit 3. Unscramble app. Dialogboks when the player goes fallit.
     * https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-viewmodel#7
     */
    private fun showDialogFallit(view: View) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.endFallit))
            .setMessage(getString(R.string.try_again))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ ->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)) { _, _ ->
                restartGame(view)
            }
            .show()
    }

    // ExitGame method when the player use the Exit in the dialog. the lykkehjule game will close.
    /** Inspiration from Cup Cake app unit 4
     * https://developer.android.com/codelabs/basic-android-kotlin-training-shared-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-4%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-shared-viewmodel#2
    */
    fun exitGame() {
        activity?.finish()

    }

    // Method restartGame is created so when the user push "SPIL IGEN" button in dialog the user can play again.
    // method calls the navigation gameFrag and sets the action to startFrag.
    // When the user use the exit method the user comes back to startFrag and can now
    // choose a new category.
    /**inspiration from Cup Cake app unit 4
     * https://developer.android.com/codelabs/basic-android-kotlin-training-shared-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-4%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-shared-viewmodel#2
    */
    private fun restartGame(view: View) {
        Navigation.findNavController(view).navigate(GameFragDirections.actionGameFragToStartFrag())
    }

}