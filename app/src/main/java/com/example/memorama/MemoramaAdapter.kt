package com.example.memorama

import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.games.R
import java.util.*


class MemoramaAdapter(val context:Context, val chips: ArrayList<Chip>, val cardKeys: HashMap<Int,String>):

    RecyclerView.Adapter<MemoramaAdapter.ChipViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChipViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.renglon, p0, false)

        return ChipViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  chips.size
    }

    override fun onBindViewHolder(p0: ChipViewHolder, p1: Int) {
        p0.imageView.setImageResource(chips[p1].idImage)
        p0.imageView.id = (chips[p1].pos)

    }


    inner class ChipViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val imageView = item.findViewById<ImageView>(R.id.chip)
        var handlerUI = Handler()


        init {
            item.setOnClickListener {

                Log.d("CHIP", "" + cardKeys[imageView.id])
                when (cardKeys[imageView.id]) {
                    "ninetales" -> imageView.setImageResource(R.drawable.ninetales)
                    "pikachu" -> imageView.setImageResource(R.drawable.pikachu)
                    "charizard" -> imageView.setImageResource(R.drawable.charizard)
                    "eevee" -> imageView.setImageResource(R.drawable.eevee)
                    "jigglypuff" -> imageView.setImageResource(R.drawable.jigglypuff)
                    "mewtwo" -> imageView.setImageResource(R.drawable.mewtwo)
                    "rapidash" -> imageView.setImageResource(R.drawable.rapidash)
                    "wobbuffet" -> imageView.setImageResource(R.drawable.wobbuffet)
                }

                handlerUI.postDelayed(Runnable {
                    if (context is MemoramaActivity) {
                        (context as MemoramaActivity).startChrono()
                        var match = (context as MemoramaActivity).turnChip(imageView, item)
                        if(match) item.isClickable = false
                    } },
                    500)
            }
        }
    }

}
