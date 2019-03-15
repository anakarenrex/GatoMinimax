package com.example.memorama

import android.annotation.TargetApi
import android.media.Image
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import com.example.games.R
import kotlinx.android.synthetic.main.activity_memorama2.*

class MemoramaActivity : AppCompatActivity() {

    val pokemon = arrayOf("charizard","eevee","jigglypuff","mewtwo",
        "ninetales","pikachu","rapidash","wobbuffet")

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorama2)

        val cardsKeys: HashMap<Int,String> = HashMap()
        val fillCards: HashMap<String,Int> = HashMap()

        for(pokemon in pokemon){
            fillCards.put(pokemon,2)
        }

        val rv = recyclerView1
        rv.setHasFixedSize(true)
        var grid = GridLayoutManager(this, 4)
        rv.layoutManager = grid

        val chips = ArrayList<Chip>()
        var randomInteger: Int
        var i=0
        loop@ while( i  < 16) {
            val rnd = (0..7).random()
            when(rnd){
                0->{
                    val temp=fillCards[pokemon[0]]
                    if(temp==0){
                        continue@loop
                    }
                    if (temp != null) {
                        fillCards.set(pokemon[0],temp-1)
                    }
                    chips.add(Chip(R.mipmap.ic_launcher, i, pokemon[0]))
                    cardsKeys.put(i,pokemon[0])
                }
                1->{
                    val temp=fillCards[pokemon[1]]
                    if(temp==0){
                        continue@loop
                    }
                    if (temp != null) {
                        fillCards.set(pokemon[1],temp-1)
                    }
                    chips.add(Chip(R.mipmap.ic_launcher, i, pokemon[1]))
                    cardsKeys.put(i,pokemon[1])
                }
                2->{
                    val temp=fillCards[pokemon[2]]
                    if(temp==0){
                        continue@loop
                    }
                    if (temp != null) {
                        fillCards.set(pokemon[2],temp-1)
                    }
                    chips.add(Chip(R.mipmap.ic_launcher, i, pokemon[2]))
                    cardsKeys.put(i,pokemon[2])
                }
                3->{
                    val temp=fillCards[pokemon[3]]
                    if(temp==0){
                        continue@loop
                    }
                    if (temp != null) {
                        fillCards.set(pokemon[3],temp-1)
                    }
                    chips.add(Chip(R.mipmap.ic_launcher, i, pokemon[3]))
                    cardsKeys.put(i,pokemon[3])
                }
                4->{
                    val temp=fillCards[pokemon[4]]
                    if(temp==0){
                        continue@loop
                    }
                    if (temp != null) {
                        fillCards.set(pokemon[4],temp-1)
                    }
                    chips.add(Chip(R.mipmap.ic_launcher, i, pokemon[4]))
                    cardsKeys.put(i,pokemon[4])
                }
                5->{
                    val temp=fillCards[pokemon[5]]
                    if(temp==0){
                        continue@loop
                    }
                    if (temp != null) {
                        fillCards.set(pokemon[5],temp-1)
                    }
                    chips.add(Chip(R.mipmap.ic_launcher, i, pokemon[5]))
                    cardsKeys.put(i,pokemon[5])
                }
                6->{
                    val temp=fillCards[pokemon[6]]
                    if(temp==0){
                        continue@loop
                    }
                    if (temp != null) {
                        fillCards.set(pokemon[6],temp-1)
                    }
                    chips.add(Chip(R.mipmap.ic_launcher, i, pokemon[6]))
                    cardsKeys.put(i,pokemon[6])
                }
                7 ->{
                    val temp=fillCards[pokemon[7]]
                    if(temp==0){
                        continue@loop
                    }
                    if (temp != null) {
                        fillCards.set(pokemon[7],temp-1)
                    }
                    chips.add(Chip(R.mipmap.ic_launcher, i, pokemon[7]))
                    cardsKeys.put(i,pokemon[7])
                }
            }
            i++
        }

        var  adapter = MemoramaAdapter(chips, cardsKeys)
        rv.adapter = adapter
    }
}