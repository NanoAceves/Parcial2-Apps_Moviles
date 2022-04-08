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

    companion object {
        private class DownloadData(context: Context, recyclerView: RecyclerView) :
            AsyncTask<String, Void, String>() {
            private val TAG = "DownloadData"

            var localContext: Context by Delegates.notNull()
            var localRecyclerView: RecyclerView by Delegates.notNull()

            init {
                localContext = context
                localRecyclerView = recyclerView
            }

            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG, "doInBackground")
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()) {
                    Log.e(TAG, "doInBackground: failed")
                }
                Log.d(TAG, rssFeed)
                return rssFeed
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                Log.d(TAG, "onPostExecute")
                val parsedApplication = ParseApplication()
                parsedApplication.parse(result)

                val adapter: ApplicationsAdapter =
                    ApplicationsAdapter(localContext, parsedApplication.applications)
                localRecyclerView.adapter = adapter
                localRecyclerView.layoutManager = LinearLayoutManager(localContext)
            }

            private fun downloadXML(urlPath: String?): String {
                return URL(urlPath).readText()
            }
        }

    }


}