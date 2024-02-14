package com.example.movieposter.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.bumptech.glide.Glide
import com.example.movieposter.Adapted.ImageListAdapter
import com.example.movieposter.Domain.Film
import com.example.movieposter.R
import com.example.movieposter.databinding.ActivityDetailBinding
import com.google.android.material.imageview.ShapeableImageView
import com.google.gson.Gson


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mRequestQueue: RequestQueue
    private lateinit var mStringRequest: StringRequest
    private lateinit var progressBar: ProgressBar
    private lateinit var movieRateTxt: TextView
    private lateinit var movieDataTxt: TextView
    private lateinit var movieTimeTxt: TextView
    private lateinit var movieSummaryInfo: TextView
    private lateinit var movieActorsInfo: TextView
    private lateinit var nestedScrollView: NestedScrollView
    private var idFilm: Int = 0

    private lateinit var shapeableImageView: ShapeableImageView
    private lateinit var imageView: ImageView
    private lateinit var backgroundImageView: ImageView
    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerView: RecyclerView
    private lateinit var titleTextView: TextView
    private lateinit var titleText: TextView
    private lateinit var backimg : ImageView
    private lateinit var likeImg : ImageView
    private lateinit var pic: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idFilm = intent.getIntExtra("id", 0)
        initView()
    }

    private fun sendRequest(idFilm: Int) {
        lateinit var adapterImgList: ImageListAdapter
        val url = "https://moviesapi.ir/api/v1/movies/$idFilm"
        val gson = Gson()

        progressBar.visibility = View.VISIBLE

        mStringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                progressBar.visibility = View.GONE
                val item: Film = gson.fromJson(response, Film::class.java)

                Glide.with(this@DetailActivity)
                    .load(item.poster)
                    .into(findViewById<ImageView>(com.example.movieposter.R.id.pic))

                titleText.text = item.title
                movieRateTxt.text = item.rated
                movieDataTxt.text = item.released
                movieTimeTxt.text = item.runtime
                movieSummaryInfo.text = item.plot
                movieActorsInfo.text = item.actors

                if (item.images != null) {
                    adapterImgList.setImages(item.images)
                }
            },
            Response.ErrorListener { error ->
                progressBar.visibility = View.GONE
                Log.i("qwe", "onErrorResponse " + error.toString())
            }
        )

        mRequestQueue.add(mStringRequest)
    }
    private fun initView() {
        recyclerView = binding.movieRecyclerView
        titleText = binding.movieTimeTxt
            // nestedScrollView = findViewById(R.id.scrollView2)
        movieRateTxt = binding.movieRiteTxt
        movieDataTxt = binding.movieDataTxt
        movieTimeTxt = binding.movieTimeTxt
        movieSummaryInfo = binding.movieSummeryInfo
        movieActorsInfo = binding.movieActorsInfo
        // pic = findViewById(R.id.pic)
        backimg = binding.backImg
        likeImg = binding.likeImg
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        backimg.setOnClickListener { finish() }

    }
}
