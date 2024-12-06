package com.apsmt.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apsmt.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), View.OnClickListener{

    private var _binding: ActivityGameBinding? = null
    private val binding get() = _binding!!

    private var gameModel: GameModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)

        binding.btnStartGame.visibility = View.VISIBLE
        binding.btnBack.visibility = View.INVISIBLE

        binding.btnStartGame.setOnClickListener {
            binding.btnStartGame.visibility = View.INVISIBLE
            binding.btnBack.visibility = View.VISIBLE
            startGame()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        GameData.gameModel.observe(this){
            gameModel = it
            setUI()
        }

    }

    private fun startGame() {
        gameModel?.apply{
            GameData.saveGameModel(
                GameModel(
                    status = Status.INPROGRESS
                )
            )
        }
    }

    private fun checkWinner(){
        val winningPosition = arrayOf(
            intArrayOf(0,1,2),
            intArrayOf(3,4,5),
            intArrayOf(6,7,8),
            intArrayOf(0,3,6),
            intArrayOf(1,4,7),
            intArrayOf(2,5,8),
            intArrayOf(0,4,8),
            intArrayOf(2,4,6)
        )

        gameModel?.apply{
            for(i in winningPosition){
                if(
                    fillPos[i[0]] == fillPos[i[1]] &&
                    fillPos[i[1]] == fillPos[i[2]] &&
                    fillPos[i[0]].isNotEmpty()
                ){
                    winner = fillPos[i[0]]
                    status = Status.FINISHED
                }
            }

            if(fillPos.none{it.isEmpty()}){
                status = Status.FINISHED
            }
            GameData.saveGameModel(this)
        }
    }

    private fun setUI(){
        gameModel?.apply{
            binding.btn1.text = fillPos[0]
            binding.btn2.text = fillPos[1]
            binding.btn3.text = fillPos[2]
            binding.btn4.text = fillPos[3]
            binding.btn5.text = fillPos[4]
            binding.btn6.text = fillPos[5]
            binding.btn7.text = fillPos[6]
            binding.btn8.text = fillPos[7]
            binding.btn9.text = fillPos[8]

            binding.txvStatus.text =
                when(status) {
                    Status.JOINED -> {
                        "Click on 'Start Game'"
                    }

                    Status.INPROGRESS -> {
                        "$currentPlayer Turn"
                    }
                    Status.FINISHED -> {
                        if(winner.isNotEmpty())"$winner Win"
                        else "Draw"
                    }
                    null -> ""
                }
        }
    }

    override fun onClick(v: View?) {
        gameModel?.apply{
            if(status != Status.INPROGRESS){
                Toast.makeText(
                    applicationContext,
                    "Game isn't start",
                    Toast.LENGTH_SHORT
                ).show()

                return
            }

            val clickPosition = (v?.tag as String).toInt()
            if(fillPos[clickPosition].isEmpty()){
                fillPos[clickPosition] = currentPlayer
                currentPlayer = if(currentPlayer == "X")"O" else "X"
                checkWinner()
                GameData.saveGameModel(this)
            }
        }
    }

}