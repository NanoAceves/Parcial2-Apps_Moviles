package com.ytplayer.segundoparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart=findViewById<Button>(R.id.btn_start)

        btnStart.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent= when(view.id){
            R.id.btn_start-> Intent(this, MoviesActivity::class.java)
            else-> throw IllegalArgumentException("Invalid Button")
        }
        startActivity(intent)
    }
}