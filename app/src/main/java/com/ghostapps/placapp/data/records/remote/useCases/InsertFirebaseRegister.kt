package com.ghostapps.placapp.data.records.remote.useCases

import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.InsertRegister
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InsertFirebaseRegister(): InsertRegister {
    override fun execute(recordModel: RecordModel) {
        Firebase.firestore
            .collection("scores")
            .document(recordModel.date.toString())
            .set(recordModel)
            .addOnFailureListener {
                it.localizedMessage ?: "Error ao inserir registro no firestore!"
            }
    }
}