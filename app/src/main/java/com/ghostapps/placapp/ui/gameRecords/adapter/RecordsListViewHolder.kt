package com.ghostapps.placapp.ui.gameRecords.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.viewModel.gameRecords.ItemScoreModel
import kotlinx.android.synthetic.main.item_game_record.view.*

class RecordsListViewHolder(itemView: View, private val onDeletePressed: (recordModel: RecordModel) -> Unit): RecyclerView.ViewHolder(itemView) {

    fun bind(record: ItemScoreModel) {
        itemView.itemGameRecordHomeTeamName.text = record.homeTeamName
        itemView.itemGameRecordHomeTeamScore.text = record.homeTeamScore.toString()
        itemView.itemGameRecordHomeTeamSetScore.text = record.homeTeamSetScore.toString()

        itemView.itemGameRecordAwayTeamName.text = record.awayTeamName
        itemView.itemGameRecordAwayTeamScore.text = record.awayTeamScore.toString()
        itemView.itemGameRecordAwayTeamSetScore.text = record.awayTeamSetScore.toString()

        itemView.itemGameRecordDelete.setOnClickListener {
            onDeletePressed(
                RecordModel(
                    homeTeamName = record.homeTeamName,
                    homeTeamScore =  record.homeTeamScore,
                    homeTeamSetScore = record.homeTeamSetScore,
                    awayTeamName = record.awayTeamName,
                    awayTeamScore = record.awayTeamScore,
                    awayTeamSetScore = record.awayTeamSetScore,
                    date = record.date
                )
            )
        }
    }

}