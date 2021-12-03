package com.example.s205360lykkehjulet.game

import androidx.lifecycle.ViewModel
import com.example.s205360lykkehjulet.Data.*
import java.lang.StringBuilder
/**
 * Collaborated whit Thamara Chellakooty s205337
 * Inspiration from Unscramble app unit 3.
 * https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-viewmodel#6
 */

class GameViewHolder : ViewModel() {
    var _point = 0
    val point: Int
        get() = _point

    var _life = 5
    val life: Int
        get() = _life

    var fieldsOnWheel = fieldsList

    // lateinit is used so the variable is defined later.
    lateinit var listOfWords: List<String>
    lateinit var field: String
    lateinit var currentWord: String
    lateinit var currentWordList: MutableList<String>
    var guessedCorrectLetters: MutableList<String> = arrayListOf()

    // Generate ta word from a list. and uses the function hideLetters to return the word with * insted of letters.
    // Assested by tutor
    fun newWord(): String {
        val currentWordNumber = (0 until (listOfWords.size)).random()
        currentWord = listOfWords[currentWordNumber]
        currentWordList = currentWord.split("").toMutableList()
        currentWordList.removeFirst()
        currentWordList.removeLast()
        val letters = hideLetters()

        var words = ""
        for (word in letters) {
            words += word

        }
        return words
    }

    // Hide the letters so the player can't see the word. And make space in the word without *.
    // Assested by tutor
    fun hideLetters(): MutableList<String> {
        var hideCurrentWordList = ""
        for (i in 0..currentWordList.size - 1) {
            if (!currentWordList.get(i).equals(" ")) {
                hideCurrentWordList += "*"
            } else {
                hideCurrentWordList += " "

            }
        }
        return hideCurrentWordList.split("").toMutableList()
    }

    // Check if the letter is in the list.
    fun checkGuess(letter: String) {
        //adds "" because spaces can not be gussed
        if ((" ") in currentWordList) guessedCorrectLetters.add(" ")

        // Check if the letter is right
        if (letter in currentWordList) {
            guessedCorrectLetters.add(letter)
            points(field)
            println(guessedCorrectLetters)
        } else wrongGuess()

    }


    // Method for winGame.
    fun winGame(): Boolean {
        if (guessedCorrectLetters.containsAll(currentWordList)) {
            return true
        }
        return false


    }

    // Sets the wheels actions eksta liv, mistet liv og fallit.
    fun wheelField(): String {
        field = fieldsOnWheel.random()

        when (field) {
            "EKSTRA LIV" -> {
                lifeUpdate()
            }
            "MISTET LIV" -> {
                wrongGuess()
            }
            "FALLIT" -> {
                fallit()
            }

        }
        return field
    }

    // Set the point actions.
    fun points(randomField: String): Int {
        when (randomField) {
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
        }
        return _point

    }


    // If fallit set life to 0.
    fun fallit() {
        _life = 0
    }


    // Updates life with +1
    private fun lifeUpdate() {
        _life += 1
    }

    // Update life with -1
    private fun wrongGuess() {
        _life -= 1


    }


    // Check if the letter is in the hidden word and shows the hidden letter if the
    // letter is in the index of currentWords.
    // and sets the word on the right position.
    // Assested by tutor
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


    // Set the different categorylist (listOfWords) on the categories
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

