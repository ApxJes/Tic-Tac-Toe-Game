package com.apsmt.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object GameData {
    val gameModel: MutableLiveData<GameModel> = MutableLiveData()

    fun saveGameModel(model: GameModel){
        gameModel.postValue(model)
    }
}