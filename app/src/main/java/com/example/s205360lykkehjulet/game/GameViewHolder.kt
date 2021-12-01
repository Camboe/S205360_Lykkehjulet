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

    //sets the fildes actions.
    fun WheelField(): String {
        field = fieldsOnWheel.random()

        when (field) {
            "EKSTRA LIV" -> {
                lifeUpdate()
            }
            "MISTET LIV" -> {
                wrongGuess()
            }
            "500 POINT" -> {
                _point += 500
            }
            "750 POINT" -> {
                _point += 750
            }
            "1000 POINT" -> {
                _point += 1000
            }
            "1500 POINT" -> {
                _point += 1500
            }
            "2000 POINT" -> {
                _point += 2000
            }
            "FALLIT" -> {
                fallit()
            }

        }
        return field
    }


    //if fallit set life to 0
    fun fallit() {
        _life = 0
    }


    //Updates life with +1
    private fun lifeUpdate() {
        _life += 1
    }

    //Update life with -1
    private fun wrongGuess() {
        _life -= 1

    var word: String = "null"

    }


    //inspiraition from unit 3 unscramble app
    fun reintializeGame() {
        _point = 0
        _life = 5

    }

    // Check if the letter is in the hidden word and shows the hidden letter if the
    // letter is in the index of currentWords.
    // and sets the word on the right position.
    fun checkWord(hideWord: String, letter: Char): String {
        var tempHideWord = hideWord
        for (i in 0..currentWord.length) {
            if (letter in currentWord) {
                var wordPosition = currentWord.indexOf(letter, i)
                if (wordPosition >= 0) {
                    val checkBuilder = StringBuilder(tempHideWord)
                    checkBuilder.setCharAt(wordPosition, letter)
                    tempHideWord = checkBuilder.toString()
                }

            }
        }

        return tempHideWord
    }


    //set the different categorylist (listOfWords) on the categories
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

