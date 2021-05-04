package com.ghostapps.placapp.viewModel.gameRecords

data class ItemScoreModel (
    val homeTeamName: String,
    val homeTeamScore: Int,
    val homeTeamSetScore: Int,

    val awayTeamName: String,
    val awayTeamScore: Int,
    val awayTeamSetScore: Int,

    val date: Long
)