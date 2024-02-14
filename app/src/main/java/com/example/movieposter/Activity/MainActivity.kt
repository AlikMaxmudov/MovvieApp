package com.example.movieposter.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.movieposter.Adapted.FilmListAdapter
import com.example.movieposter.Domain.Film
import com.example.movieposter.R
import com.example.movieposter.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewNewMovie: RecyclerView
    private lateinit var recyclerViewUpComming: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterNewMovie: FilmListAdapter
    private lateinit var adapterUpComming: FilmListAdapter
    private lateinit var mRequstQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initView()
        sendRequest()
        sendRequest2()
    }

    data class FilmResponse(
        @SerializedName("data") val films: List<Film>?
    )

    private fun sendRequest() {
        mRequstQueue = Volley.newRequestQueue(this)
        val mStringRequest = StringRequest(
            Request.Method.GET,
            "https://moviesapi.ir/api/v1/movies?page=1",
            { responce ->
                val gson = Gson()
//              binding.loadingnewfilms.visibility = View.GONE

                val FilmResponse = gson.fromJson(responce, FilmResponse::class.java)
                adapterNewMovie = FilmListAdapter(FilmResponse.films ?: emptyList())
                recyclerViewNewMovie.adapter = (adapterNewMovie)
                adapterNewMovie.notifyDataSetChanged()
                Log.i("FilmResponce" ,FilmResponse.toString())


            },
            { error ->
                Log.i("qweqwe", "sendRequest1:  " + error.toString())
                //binding.loadingnewfilms

            },
        )
        mRequstQueue.add(mStringRequest)
    }

    private fun sendRequest2() {
        mRequstQueue = Volley.newRequestQueue(this)
        val mStringRequest = StringRequest(
            Request.Method.GET,
            "https://moviesapi.ir/api/v1/movies?page=3",
            { responce ->
                val gson = Gson()
                binding.loadingnextfilms.visibility = View.GONE
                val FilmResponse = gson.fromJson(responce, FilmResponse::class.java)
                adapterUpComming = FilmListAdapter(FilmResponse.films ?: emptyList())
                recyclerViewUpComming.adapter = adapterUpComming
                adapterUpComming.notifyDataSetChanged()
                Log.i("FilmResponce" ,FilmResponse.toString())
            },
            { error ->
                binding.loadingnextfilms

            },
        )
        mRequstQueue.add(mStringRequest)
    }

    private fun initView() {

        recyclerViewNewMovie = binding.newfilms
        recyclerViewUpComming = binding.nextFilm
        recyclerViewNewMovie.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewUpComming.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewUpComming.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)



    }


}
