package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener{
    //2D array 3X3
    lateinit var board:Array<Array<Button>>
    //Player 1=true Player 2 =false
    var PLAYER=true
    var TurnCount=0
    var first=true
    var boardStatus=Array(3){IntArray(3)}//creating a 3x3 array and will update the status here
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for(i in board){
            for(but in i){
                but.setOnClickListener(this)
            }
        }
        initilizeBoardStatus()
        resetbtn.setOnClickListener {
            initilizeBoardStatus()
            TurnCount=0
            PLAYER=true
        }
    }

    private fun initilizeBoardStatus() {
        for(i in 0..2){
            for (j in 0..2){
                boardStatus[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""

            }
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.button1->{
                    updateValue(row=0,col=0,plauer=PLAYER)

                }
                R.id.button2->{
                    updateValue(row=0,col=1,plauer=PLAYER)

                }
                R.id.button3->{
                    updateValue(row=0,col=2,plauer=PLAYER)

                }
                R.id.button4->{
                    updateValue(row=1,col=0,plauer=PLAYER)

                }
                R.id.button5->{
                    updateValue(row=1,col=1,plauer=PLAYER)

                }
                R.id.button6->{
                    updateValue(row=1,col=2,plauer=PLAYER)

                }
                R.id.button7->{
                    updateValue(row=2,col=0,plauer=PLAYER)

                }
                R.id.button8->{
                    updateValue(row=2,col=1,plauer=PLAYER)

                }
                R.id.button9->{
                    updateValue(row=2,col=2,plauer=PLAYER)

                }
            }
        }
        PLAYER=!PLAYER
        TurnCount++
        if(PLAYER==first){
            updateDisplay("PLAYER X TURN")
        }else if(PLAYER!=first){
            updateDisplay("Player Y Turn")
        }
        if(TurnCount==9) {
            updateDisplay("GAME DRAWN")

        }
        checkWinner()
    }

    private fun checkWinner() {
       //Horizontal Rows
        for(i in 0..2){
           if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
               if(boardStatus[i][0]==1)
                   updateDisplay("WINNER IS X")
               else if (boardStatus[i][0]==0)
                   updateDisplay("WINNER IS Y")
           }
        }
        //vertical cols
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1)
                    updateDisplay("WINNER IS X")
                else if (boardStatus[0][i]==0)
                    updateDisplay("WINNER IS Y")
            }
        }
        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2]){
            if(boardStatus[1][1]==1)
                updateDisplay("WINNER IS X")
            else if (boardStatus[1][1]==0)
                updateDisplay("WINNER IS Y")
        }
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][0]){
            if(boardStatus[1][1]==1)
                updateDisplay("WINNER IS X")
            else if (boardStatus[1][1]==0)
                updateDisplay("WINNER IS Y")
        }
    }

    private fun disablebtn() {
        for(i in board){
            for(btn in i){
                btn.isEnabled=false
            }
        }
    }

    private fun updateDisplay(s: String) {
        displayTV.text=s
        if(displayTV.text.contains("WINNER"))
            disablebtn()
    }

    private fun updateValue(row: Int, col: Int, plauer: Boolean) {
        val text:String=if(plauer==first)"X" else {
            "O"
        }
        val value:Int=if(plauer==first) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]=value
    }
}