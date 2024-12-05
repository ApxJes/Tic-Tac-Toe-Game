package com.apsmt.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apsmt.tictactoe.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener {
            createGame()
        }
    }

    private fun createGame() {
        GameData.saveGameModel(
            GameModel(
                status = Status.JOINED
            )
        )
        startGame()
    }

    private fun startGame() {
        Intent(
            this,
            GameActivity::class.java
        ).also {
            startActivity(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}