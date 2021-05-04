package com.ghostapps.placapp.domain.useCases

import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.viewModel.gameRecords.ItemScoreModel

interface DeleteRegister {
    fun execute(successCallback: (recordModel: RecordModel) -> Unit, recordModel: RecordModel)
}