package com.ytplayer.segundoparcial

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException
import java.net.URL
import kotlin.properties.Delegates


class MoviesActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val recView: RecyclerView = findViewById(R.id.xmlRecyclerView)

        val movies = ArrayList<MovieModel>()
        val titles: Array<String> = resources.getStringArray(R.array.title)
        val years: Array<String> = resources.getStringArray(R.array.year)
        val directors: Array<String> = resources.getStringArray(R.array.director)
        val genres: Array<String> = resources.getStringArray(R.array.genre)
        val languages: Array<String> = resources.getStringArray(R.array.language)
        val metascores: Array<String> = resources.getStringArray(R.array.metascore)
        val imdbRatings: Array<String> = resources.getStringArray(R.array.imdbRating)
        val imdbVotes: Array<String> = resources.getStringArray(R.array.imdbVotes)

        for((i, element) in titles.withIndex()){
            val movie = MovieModel()
            movie.title = element
            movie.year = years[i]
            movie.director = directors[i]
            movie.genre = genres[i]
            movie.language = languages[i]
            movie.metascore = metascores[i]
            movie.imdbRating = imdbRatings[i]
            movie.imdbVotes = imdbVotes[i]
            movies.add(movie)
        }

        val adapter: ApplicationsAdapter =
            ApplicationsAdapter(this, movies)
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(this)

        val btnExit=findViewById<Button>(R.id.btn_exit)

        btnExit.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val intent= when(view.id){
            R.id.btn_start-> Intent(this, MoviesActivity::class.java)
            else-> throw IllegalArgumentException("Invalid Button")
        }
        startActivity(intent)
    }



}