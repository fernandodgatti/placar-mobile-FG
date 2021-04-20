package com.ghostapps.placapp.ui.gameScore

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ghostapps.placapp.R
import com.ghostapps.placapp.databinding.ActivityHomeBinding
import com.ghostapps.placapp.databinding.ActivityScoreGameBinding
import com.ghostapps.placapp.viewModel.gameScore.GameScoreContract
import com.ghostapps.placapp.viewModel.gameScore.GameScoreViewModel
import com.ghostapps.placapp.viewModel.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_score_game.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GameScoreActivity: AppCompatActivity(), GameScoreContract {

    companion object {
        const val TEAM_HOME_NAME = "home_team_name"
        const val TEAM_AWAY_NAME = "away_team_name"
    }

    private lateinit var binding: ActivityScoreGameBinding
    private val viewModel: GameScoreViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_score_game)
        binding.viewModel = viewModel

        viewModel.onCreate(intent.getStringExtra(TEAM_HOME_NAME) ?: "", intent.getStringExtra(TEAM_AWAY_NAME) ?: "")
    }

    override fun onExitPressed() {
        finish()
    }

    override fun finishGame() {
//Professor tentei usar o alert dialog abaixo comentado, vi sua aula novamente mas não rolou da o seguinte erro abaixo, estou comentando o código e vou tirar essa duvida com você
//        04-19 22:07:10.269 4278-4325/com.ghostapps.placapp E/AndroidRuntime: FATAL EXCEPTION: Thread-292
//        Process: com.ghostapps.placapp, PID: 4278
//        java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
//        at android.os.Handler.<init>(Handler.java:200)
//        at android.os.Handler.<init>(Handler.java:114)
//        at android.app.Dialog.<init>(Dialog.java:119)
//        at android.app.Dialog.<init>(Dialog.java:168)
//        at androidx.appcompat.app.AppCompatDialog.<init>(AppCompatDialog.java:57)
//        at androidx.appcompat.app.AlertDialog.<init>(AlertDialog.java:98)
//        at androidx.appcompat.app.AlertDialog$Builder.create(AlertDialog.java:983)
//        at com.ghostapps.placapp.ui.gameScore.GameScoreActivity.finishGame(GameScoreActivity.kt:47)
//        at com.ghostapps.placapp.viewModel.gameScore.GameScoreViewModel.finishGame(GameScoreViewModel.kt:89)
//        at com.ghostapps.placapp.viewModel.gameScore.GameScoreViewModel.updateSet(GameScoreViewModel.kt:103)
//        at com.ghostapps.placapp.viewModel.gameScore.GameScoreViewModel.access$updateSet(GameScoreViewModel.kt:10)
//        at com.ghostapps.placapp.viewModel.gameScore.GameScoreViewModel$saveRecords$1.run(GameScoreViewModel.kt:128)
//        at java.lang.Thread.run(Thread.java:818)
//        var dialog: AlertDialog? = null
//        dialog = AlertDialog.Builder(this)
//            .setTitle("Remover Registro")
//            .setMessage("Você tem certeza que deseja remover esse item? A operação não poderá ser desfeita!")
//            .setNeutralButton("Voltar para a home") { _, _ ->
//                finish()
//                dialog?.cancel()
//            }.create()
//        dialog.show()

        finish()
    }

}