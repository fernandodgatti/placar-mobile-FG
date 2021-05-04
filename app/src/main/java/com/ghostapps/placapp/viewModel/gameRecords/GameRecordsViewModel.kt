package com.ghostapps.placapp.viewModel.gameRecords

import androidx.lifecycle.MutableLiveData
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.DeleteRegister
import com.ghostapps.placapp.domain.useCases.GetAllRegister
import com.ghostapps.placapp.viewModel.BaseViewModel

class GameRecordsViewModel(
    private val getAllRegister: GetAllRegister,
    private val deleteRegister: DeleteRegister
): BaseViewModel() {

    val recordsList = MutableLiveData<List<ItemScoreModel>>()

    fun loadRecords() {
        Thread {
            getAllRegister.execute (
                successCallback = {
                    val recordsModel = it.flatMap { recordModel ->
                    val itemScoreList = arrayListOf<ItemScoreModel>()

                    itemScoreList.add(
                        ItemScoreModel(
                            homeTeamName = recordModel.homeTeamName,
                            homeTeamScore = recordModel.homeTeamScore,
                            homeTeamSetScore = recordModel.homeTeamSetScore,
                            awayTeamName = recordModel.awayTeamName,
                            awayTeamScore = recordModel.awayTeamScore,
                            awayTeamSetScore = recordModel.awayTeamSetScore,
                            date = recordModel.date

                        )
                    )
                        itemScoreList
                    }
                    recordsList.postValue(recordsModel)
            })
        }.start()
    }

    fun deleteRegister(recordModel: RecordModel) {
        Thread {
            deleteRegister.execute(
                successCallback = { loadRecords() },
                recordModel = recordModel
            )
        }.start()
    }

}