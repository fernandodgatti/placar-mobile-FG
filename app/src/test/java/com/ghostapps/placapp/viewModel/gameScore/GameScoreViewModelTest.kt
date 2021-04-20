package com.ghostapps.placapp.viewModel.gameScore

import com.ghostapps.placapp.domain.useCases.InsertRegister
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameScoreViewModelTest {

    private val gameScoreContractMock: GameScoreContract = mock {}
    private val insertRegisterMock: InsertRegister = mock {
        given { it.execute(any()) }.willReturn { true }
    }

    private lateinit var sut: GameScoreViewModel

    @Before
    fun setup() {
        sut = GameScoreViewModel(gameScoreContractMock, insertRegisterMock)
    }

    @Test
    fun `Should update team names correctly`() {
        val teamAName = "team_a"
        val teamBName = "team_b"

        sut.onCreate(teamAName, teamBName)

        assertEquals(sut.homeTeamName, teamAName)
        assertEquals(sut.awayTeamName, teamBName)
    }

    @Test
    fun `Should increase home team score when onHomeTeamIncrease is called`() {
        sut.onHomeTeamIncrease()
        assertEquals(sut.formattedHomeTeamScore, "01")

        sut.onHomeTeamIncrease()
        assertEquals(sut.formattedHomeTeamScore, "02")
    }

    @Test
    fun `Should increase home team score when onAwayTeamIncrease is called`() {
        sut.onAwayTeamIncrease()
        assertEquals(sut.formattedAwayTeamScore, "01")

        sut.onAwayTeamIncrease()
        assertEquals(sut.formattedAwayTeamScore, "02")
    }

    @Test
    fun `Should increase home team score when onHomeTeamDecrease is called`() {
        sut.onHomeTeamDecrease()
        assertEquals(sut.formattedHomeTeamScore, "02")

        sut.onHomeTeamDecrease()
        assertEquals(sut.formattedHomeTeamScore, "01")
    }

    @Test
    fun `Should increase home team score when onAwayTeamDecrease is called`() {
        sut.onAwayTeamDecrease()
        assertEquals(sut.formattedHomeTeamScore, "02")

        sut.onHomeTeamIncrease()
        assertEquals(sut.formattedHomeTeamScore, "01")
    }

    @Test
    fun `Should increase home team Set score when onHomeTeamSetScore is called`() {
    //Não consegui chamar minhas funções criadas por serem privadas, o que eu deveria fazer em relação há isso, uma vez que as funções são desencadeadas por outras.
    }

//    @Test
//    fun `Should call onInsertRegisterFails when insertRegister fails`() {
//        val insertRegisterFailsMock: InsertRegister = mock {
//            given { it.execute(any()) }.willReturn { true }
//        }
//
//        sut = GameScoreViewModel(gameScoreContractMock, insertRegisterFailsMock)
//
//        sut.onExitPressed()
//
//       verify(gameScoreContractMock, times(1)).onInsertRegisterFails()
//    }





}