package com.ghostapps.placapp.data.records.remote.useCases

import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.DeleteRegister

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DeleteFirebaseRegister: DeleteRegister {
    override fun execute(successCallback: (recordModel: RecordModel) -> Unit, recordModel: RecordModel) {

        Firebase.firestore
            .collection("scores")
            .document(recordModel.date.toString())
            .delete()
            .addOnSuccessListener {
                println("Item deletado com sucesso!")
                successCallback(recordModel)
            }
            .addOnFailureListener {
                it.localizedMessage ?: "Error ao deletar registro no firestore!"
            }

    }
}