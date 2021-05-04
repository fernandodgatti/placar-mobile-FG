package com.ghostapps.placapp.data.records.remote.useCases

import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.GetAllRegister
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import okhttp3.internal.wait

class GetAllFirebaseRegister: GetAllRegister {
    override fun execute(successCallback: (recordList: Array<RecordModel>) -> Unit) {
        var scoreList = arrayListOf<RecordModel>()

        Firebase.firestore
            .collection("scores")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    scoreList.add(document.toObject(RecordModel::class.java))
                }
                successCallback(scoreList.toArray(arrayOfNulls<RecordModel>(0)))
            }
            .addOnFailureListener {
                it.localizedMessage ?: "Error ao consultar registro no firestore!"
            }
    }
}