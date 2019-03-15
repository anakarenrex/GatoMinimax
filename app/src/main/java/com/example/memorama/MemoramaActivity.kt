package com.example.memorama

import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import com.example.games.R
import kotlinx.android.synthetic.main.activity_memorama2.*

class MemoramaActivity : AppCompatActivity() {

    val cardsArray = intArrayOf(101,102,103,104,105,106,201,202,203,204,205,206)
    var cardNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorama2)
        val rv = recyclerView1
        rv.setHasFixedSize(true)
        var grid = GridLayoutManager(this, 4)
        rv.layoutManager = grid

        val chips = ArrayList<Chip>()
        for( i  in 0..15)
            chips.add(Chip(R.mipmap.ic_launcher))
        var  adapter = MemoramaAdapter(chips)
        rv.adapter = adapter
    }
}
