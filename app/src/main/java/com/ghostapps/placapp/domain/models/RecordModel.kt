package com.ghostapps.placapp.domain.models

class RecordModel(
    val homeTeamName: String,
    val homeTeamScore: Int,
    val homeTeamSetScore: Int,

    val awayTeamName: String,
    val awayTeamScore: Int,
    val awayTeamSetScore: Int,

    val date: Long
)