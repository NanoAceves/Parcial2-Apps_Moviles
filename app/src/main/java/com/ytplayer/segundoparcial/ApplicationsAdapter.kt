package com.ytplayer.segundoparcial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplicationsAdapter (context: Context, feedEntries: ArrayList<MovieModel>):
    RecyclerView.Adapter<ApplicationsAdapter.ViewHolder>() {
    private var localContext: Context? = null
    private var localFeedEntries: ArrayList<MovieModel>?=null

    init{
        localContext=context
        localFeedEntries=feedEntries
    }

    override fun onBindViewHolder(holder: ApplicationsAdapter.ViewHolder, position: Int) {
        val currentFeedEntry: MovieModel= localFeedEntries!![position]
        holder.textName.text=currentFeedEntry.title
        holder.textDire.text=currentFeedEntry.director
        holder.textLang.text=currentFeedEntry.language
        holder.textYear.text=currentFeedEntry.year
    }

    override fun getItemCount(): Int {
        return localFeedEntries?.size ?: 0
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val textName: TextView =v.findViewById(R.id.text_name)
        val textLang: TextView =v.findViewById(R.id.text_lang)
        val textYear: TextView =v.findViewById(R.id.text_year)
        val textDire: TextView =v.findViewById(R.id.text_director)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationsAdapter.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(localContext)
        val view: View =layoutInflater.inflate(R.layout.activity_repetible, parent, false)
        return ViewHolder(view)
    }
}