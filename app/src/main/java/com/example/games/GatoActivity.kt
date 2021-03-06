package com.example.games

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.collections.ArrayList


class GatoActivity : AppCompatActivity() {

    /*
*
* Completa el código
*
* Crea un Tablero y un JugadorAutomatic
* */

    val tablero = Tablero()
    val auto = JugadorAutomatic(tablero)
    var buttons = listOf<Button>()
    var finished = false

    var player = 1;
    var p1 = ArrayList<Int>()
    var p2 = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttons = listOf(findViewById(R.id.button),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9))


        auto.asignaFicha(Ficha.BOLA)
        /*
        * Completa el códig:
        *
        * asigna a tu JugadorAutomatic la Ficha con la que juega.
        * * */
    }

    fun gameOn(buttonCode:Int, selectedButton:Button){
        if(player == 1){
            selectedButton.text = "X"
            selectedButton.setBackgroundResource(R.color.blue)
            p1.add(buttonCode)
            player = 2


        }else if(player == 2){
            selectedButton.text = "O"
            selectedButton.setBackgroundResource(R.color.green)
            p2.add(buttonCode)
            player = 1
        }

        selectedButton.isEnabled = false
    }



    fun andTheWinnerIs(player :ArrayList<Int>): Boolean{
        var win :Boolean = false
        if(player.contains(1) && player.contains(2)  && player.contains(3) ) win = true
        if (player.contains(4)&&player.contains(5)&&player.contains(6)) win = true
        if (player.contains(7)&&player.contains(8)&&player.contains(9)) win = true
        if (player.contains(1)&&player.contains(4)&&player.contains(7)) win = true
        if (player.contains(2)&&player.contains(5)&&player.contains(8)) win = true
        if (player.contains(3)&&player.contains(6)&&player.contains(9)) win = true
        if (player.contains(1)&&player.contains(5)&&player.contains(9)) win = true
        if (player.contains(3)&&player.contains(5)&&player.contains(7)) win = true

        return win

    }


    fun select(view: View) {
        val selectedButton = view as Button
        var buttonCode = 0
        when(selectedButton.id){
            R.id.button -> buttonCode = 1
            R.id.button2 -> buttonCode = 2
            R.id.button3 -> buttonCode = 3
            R.id.button4 -> buttonCode = 4
            R.id.button5 -> buttonCode = 5
            R.id.button6-> buttonCode = 6
            R.id.button7-> buttonCode = 7
            R.id.button8-> buttonCode = 8
            R.id.button9-> buttonCode =9
        }



        /*
   Completa el código:

   Llama a la función de "move" utilzando un Handler().postDelayed de 1 segundo

    * */
        checkWin()
        gameOn(buttonCode, selectedButton)
        if(!finished) {
            Handler().postDelayed({
                move()
            }, 500)
        } else {
            restart()
        }

    }


    /*
    Completa el código:

    Debes mapear el renglo y la columna para regrar un Pair.... WHAAAAATTTTT, investiga qué es un Pair

    El Pair debe regresar:
        1. el número de la ficha, del 1 al 9
        2. el Button correspondiente con la interfaz

     * */
    fun nextFicha(renglon : Int, columna : Int) : Pair<Int, Button> {
        var number = 0

        if(renglon == 0) {
            when (columna) {
                0 -> number = 1
                1 -> number = 2
                2 -> number = 3
            }
        }
        if(renglon == 1) {
            when (columna) {
                0 -> number = 4
                1 -> number = 5
                2 -> number = 6
            }
        }
        if(renglon == 2) {
            when (columna) {
                0 -> number = 7
                1 -> number = 8
                2 -> number = 9
            }
        }

        var pair = Pair(number, buttons[number-1])
        return pair
    }

    /*
    Completa el código:

    Completa la funcjón y realiza lo siguiente:
    1. Ejecuta: ___TU JUGADORAUTOMATIC__.tablero.setTablero(p1, p2)
    2. Obten el siguiente movimiento automático
    3. Ejecuta la función de nextFicha y obten el pair
    4. Manda el pair a gameOn como corresponde

     * */

    fun checkWin(){
        if (andTheWinnerIs(p1) ){
            Toast.makeText(this,
                "AND the winner is PLAYER 1", Toast.LENGTH_LONG).show()
            finished=true
        }else if(andTheWinnerIs(p2)){
            Toast.makeText(this,
                "AND the winner is PLAYER 2", Toast.LENGTH_LONG).show()
            finished=true
        }else if(tablero.empate()){
            Toast.makeText(this,
                "TIE!", Toast.LENGTH_LONG).show()
            finished=true
        }
    }

    fun move(){

        auto.tablero.setTablero(p1, p2)
        checkWin()
        if(!finished) {
            var array = auto.calculaMovimiento()
            var pair = nextFicha(array!![0], array[1])
            gameOn(pair.first, pair.second)
            checkWin()
            if(finished){
                restart()
            }
        } else{
            restart()
        }
    }

    fun restart(){
        Handler().postDelayed({
            if(finished){
                tablero.init()
                p1.clear()
                p2.clear()
                player = 1
                finished = false
                for(button in buttons){
                    button.text = ""
                    button.isEnabled = true
                    button.setBackgroundResource(R.color.material_grey_100)
                }
            }
        }, 500)

    }
}
