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

    //hide the letters so the player can't see the word.
    fun hideLetters(): MutableList<String> {
        var hideCurrentWordList = ""
        for (i in 1..currentWordList.size) {
            hideCurrentWordList += "*"
        }
        return hideCurrentWordList.split("").toMutableList()
    }

    //check if the letter is in the list.
    fun checkGuess(letter: String) {
        //adds "" because spaces can not be gussed
        if ((" ") in currentWordList) guessedCorrectLetters.add(" ")

        //check if the letter is right
        if (letter in currentWordList) {
            guessedCorrectLetters.add(letter)
            println(guessedCorrectLetters)
        } else wrongGuess()
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
                listOfWords = famousList
            }
            "Bogtitler" -> {
                listOfWords = bookTitlesList
            }
            "Sport" -> {
                listOfWords = sportList
            }
            "Turistseværdigheder" -> {
                listOfWords = seveardighederList
            }

        }
        return newWord()
    }
}

