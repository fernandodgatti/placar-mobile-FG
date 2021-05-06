package com.ghostapps.placapp.viewModel.gameScore

import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.InsertRegister
import com.ghostapps.placapp.ui.gameRecords.GameRecordsActivity
import com.ghostapps.placapp.ui.home.HomeActivity
import com.ghostapps.placapp.viewModel.BaseViewModel
import java.util.*

class GameScoreViewModel(
    private val contract: GameScoreContract,
    private val insertRegister: InsertRegister
): BaseViewModel() {

    var homeTeamName = ""
    var awayTeamName = ""

    private var homeTeamScore = 0
    private var awayTeamScore = 0
    private var homeTeamSetScore = 0
    private var awayTeamSetScore = 0

    var formattedHomeTeamScore = "00"
    var formattedAwayTeamScore = "00"
    var formattedHomeTeamSetScore = "0"
    var formattedAwayTeamSetScore = "0"

    fun onCreate(homeTeamName: String, awayTeamName: String) {
        this.homeTeamName = homeTeamName
        this.awayTeamName = awayTeamName
    }

    fun onHomeTeamIncrease() {
        if (homeTeamScore < 25){
            homeTeamScore++
            updateScore()

            var rulerDifferenceTwoPoints = homeTeamScore - awayTeamScore
            var isTieBreaker = homeTeamSetScore == 2 && awayTeamSetScore == 2

            if (!isTieBreaker) {
                if (homeTeamScore >= 25 && rulerDifferenceTwoPoints > 1) {
                    homeTeamSetScore++
                    saveRecords()

                }
            } else {
                if (homeTeamScore >= 15 && rulerDifferenceTwoPoints > 1) {
                    homeTeamSetScore++
                    saveRecords()
                }
            }
        }
    }

    fun onHomeTeamDecrease() {
        if (homeTeamScore > 0) homeTeamScore--
        updateScore()
    }

    fun onAwayTeamIncrease() {
        if (awayTeamScore < 25) {
            awayTeamScore++
            updateScore()

            var rulerDifferenceTwoPoints = awayTeamScore - homeTeamScore
            var isTieBreaker = awayTeamSetScore == 2 && homeTeamSetScore == 2

            if (!isTieBreaker) {
                if (awayTeamScore >= 25 && rulerDifferenceTwoPoints > 1) {
                    awayTeamSetScore++
                    saveRecords()
                }
            } else {
                if (awayTeamScore >= 15 && rulerDifferenceTwoPoints > 1) {
                    awayTeamSetScore++
                    saveRecords()
                }
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

    fun finishGame() {
        contract.finishGame()
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
            finishGame()
        }

        formattedHomeTeamSetScore = homeTeamSetScore.toString()
        formattedAwayTeamSetScore = awayTeamSetScore.toString()

        homeTeamScore = 0
        awayTeamScore = 0

        updateScore()


    }

    private fun saveRecords() {
        Thread {
            insertRegister.execute(RecordModel(
                homeTeamName = homeTeamName,
                homeTeamScore = homeTeamScore,
                homeTeamSetScore = homeTeamSetScore,
                awayTeamName = awayTeamName,
                awayTeamScore = awayTeamScore,
                awayTeamSetScore = awayTeamSetScore,
                date = Date().time
            ))
            updateSet()
        }.start()
    }

}