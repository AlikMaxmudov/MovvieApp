package com.example.movieposter.Adapted

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieposter.Activity.DetailActivity
import com.example.movieposter.Domain.Film
import com.example.movieposter.R
import com.example.movieposter.databinding.ViewholderFilmBinding


class FilmListAdapter(private val items: List<Film>) : RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {


    lateinit var context: Context
    var FilmListAdapter: List<Film> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_film, parent, false)
        context = parent.getContext()
        Log.i("OnCreateViewHolder", "Creating view for position: $viewType")
        return ViewHolder(inflate)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var film = items[position]
        holder.titleText.text = film.title
        holder.scoreTxt.text = "" + film.imdbRating

        Glide.with(holder.itemView.context)
            .load(film.poster)
            .into(holder.pic)
            Log.i("onBind", "Binding film: " + film.title)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("id", film.id)
            holder.itemView.context.startActivity(intent)
        }

    }

override fun getItemCount(): Int {
    Log.i("getItemCount", "" + items.size)
    return items.size
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var binding: ViewholderFilmBinding
    var titleText: TextView
    var scoreTxt: TextView
    var pic: ImageView

    init {
        binding = ViewholderFilmBinding.bind(itemView)
        titleText = binding.titleText
        scoreTxt = binding.scoreTxt
        pic = binding.pic

    }
}

}


