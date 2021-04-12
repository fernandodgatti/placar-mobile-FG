package com.ghostapps.placapp.viewModel.gameScore

import android.app.AlertDialog
import com.ghostapps.placapp.viewModel.BaseViewModel

class  GameScoreViewModel(
    private val contract: GameScoreContract
): BaseViewModel() {

    var homeTeamName = ""
    var awayTeamName = ""

    var homeTeamScore = 0
    var awayTeamScore = 0
    var homeTeamSetScore = 0
    var awayTeamSetScore = 0

    var formattedHomeTeamScore = "00"
    var formattedAwayTeamScore = "00"
    var formattedHomeTeamSetScore = "0"
    var formattedAwayTeamSetScore = "0"

    fun onCreate(homeTeamName: String, awayTeamName: String) {
        this.homeTeamName = homeTeamName
        this.awayTeamName = awayTeamName
    }

    fun onHomeTeamIncrease() {
        homeTeamScore++
        updateScore()

        var rulerDifferenceTwoPoints = homeTeamScore - awayTeamScore
        var isTieBreaker = homeTeamSetScore == 2 && awayTeamSetScore == 2

        if (!isTieBreaker) {
            if (homeTeamScore >= 25 && rulerDifferenceTwoPoints > 1) {
                homeTeamSetScore++
                updateSet()
            }
        } else {
            if (homeTeamScore >= 15 && rulerDifferenceTwoPoints > 1) {
                homeTeamSetScore++
                updateSet()
            }
        }
    }

    fun onHomeTeamDecrease() {
        if (homeTeamScore > 0) homeTeamScore--
        updateScore()
    }

    fun onAwayTeamIncrease() {
        awayTeamScore++
        updateScore()

        var rulerDifferenceTwoPoints = awayTeamScore - homeTeamScore
        var isTieBreaker = awayTeamSetScore == 2 && homeTeamSetScore == 2

        if(!isTieBreaker) {
            if (awayTeamScore >= 25 && rulerDifferenceTwoPoints > 1) {
                awayTeamSetScore++
                updateSet()
            }
        } else {
            if (awayTeamScore >= 15 && rulerDifferenceTwoPoints > 1) {
                awayTeamSetScore++
                updateSet()
            }
        }

    }

    fun onAwayTeamDecrease() {
        if (awayTeamScore > 0) awayTeamScore--
        updateScore()
    }

    fun onExitPressed() {
        contract.onExitPressed()
    }

    private fun updateScore() {
        formattedHomeTeamScore = String.format("%02d", homeTeamScore)
        formattedAwayTeamScore = String.format("%02d", awayTeamScore)

        notifyChange()
    }

    private fun updateSet() {
        if (homeTeamSetScore == 3 || awayTeamSetScore == 3) {
            homeTeamSetScore = 0
            awayTeamSetScore = 0
//            FALTOU UM ALERT DIALOG NESSA LINHA QUANDO O JOGO ACABAR FALANDO O TIME QUE GANHOU, MAS NÃO CONSEGUI FAZER PROFESSOR
        }

        formattedHomeTeamSetScore = homeTeamSetScore.toString()
        formattedAwayTeamSetScore = awayTeamSetScore.toString()

        homeTeamScore = 0
        awayTeamScore = 0
        updateScore()
    }
}