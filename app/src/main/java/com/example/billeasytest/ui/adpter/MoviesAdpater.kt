package com.example.billeasytest.ui.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.billeasytest.R
import com.example.billeasytest.databinding.MoviesChildLytBinding
import com.example.billeasytest.model.Result

class MoviesAdapter(  context : Context , moviesList : List<Result>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val context = context
    private val moviesList = moviesList




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movies_child_lyt,parent,false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return  moviesList.size
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val bind = MoviesChildLytBinding.bind(itemView)
    }
}