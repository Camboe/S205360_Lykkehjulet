package com.example.s205360lykkehjulet.game

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.s205360lykkehjulet.Data.wordList

class GameViewHolder : ViewModel() {
    private var _point = 0
    val point: Int
    get() = _point

    private var _life = 0
    val life: Int
    get() = _life


    private val listOfWords = wordList

    //lateinit benyttes til at variablen først bliver defineret senere

    lateinit var currentWordList: MutableList<String>


    fun newWord(): MutableList<String> {
        var currentWordNumber = (0 until (wordList.size)).random()
        var currentWord = listOfWords[currentWordNumber]
        currentWordList = currentWord.split("").toMutableList()
        currentWordList.removeFirst()
        currentWordList.removeLast()
        print(currentWordList)

        return currentWordList

    }

    lateinit var quessedCorrectLetters: MutableList<String>

    fun checkGuess(letter: String) {
        //Der tilføjes "" da mellemrum ikke kan gættes
        if ((" ") in currentWordList) quessedCorrectLetters.add(" ")

        //Tjek om det er det rigtige bogstav
        if (letter in currentWordList) {
            quessedCorrectLetters.add(letter)
        }
    }

    //Metode hvis man har vundet
    fun winGame() {
        if (quessedCorrectLetters.containsAll(currentWordList))
            return
    }

    //skal evt. implementeres
//init {
    //Log.d("GameFrag", "GameViewHolder created!")}

    var word: String = "null"

    fun generateWord(category: String): String {
        when (category) {
            "Kendte" -> {
                word = "Kendte"
                return word
            }
            "Bogtitler" -> {
                word = "Bogtitler"
                return word
            }
            "Sport" -> {
                word = "Sport"
                return word
            }
            "Turistseværdigheder" -> {
                word = "Turistseværdigheder"
                return word
            }

            else -> {
                word = "Intet"
                return word
            }
        }
    }
}

