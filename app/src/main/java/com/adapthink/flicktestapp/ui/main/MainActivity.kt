package com.adapthink.flicktestapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adapthink.flicktestapp.R
import com.adapthink.flicktestapp.core.data.Resource
import com.adapthink.flicktestapp.core.util.AdapterUtil
import com.adapthink.flicktestapp.core.util.ConstGlobal.INTENT_DETAIL
import com.adapthink.flicktestapp.core.util.ConstGlobal.convertWaktuNews
import com.adapthink.flicktestapp.databinding.ActivityMainBinding
import com.adapthink.flicktestapp.ui.detail.DetailNewsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_berita.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.news.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rlNews.visibility = View.GONE
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rlNews.visibility = View.VISIBLE
                        binding.tvJudul.text = it.data!![0].title

                        binding.tvDeskripsi.text = it.data[0].description

                        binding.tvTanggal.text = convertWaktuNews(it.data[0].publishedAt)

                        if (it.data[0].urlToImage != "") {
                            Glide.with(this)
                                .load(it.data[0].urlToImage)
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .dontAnimate()
                                .into(binding.imageNews2)
                        }
                        val adapter = AdapterUtil(
                            R.layout.item_list_berita,
                            it.data,
                            { _, itemView, item ->
                                itemView.tv_judulnews.text = item.title
                                itemView.tv_deskripsi_news.text = item.description
                                itemView.tv_tanggal_news.text = convertWaktuNews(item.publishedAt)
                                if (item.urlToImage != "") {
                                    Glide.with(this)
                                        .load(item.urlToImage)
                                        .dontAnimate()
                                        .into(itemView.imageViewListNews)
                                }
                            },
                            { _, item ->
                                val intent = Intent(this, DetailNewsActivity::class.java)
                                intent.putExtra(INTENT_DETAIL, item)
                                startActivity(intent)
                            })

                        binding.rvNews.apply {
                            this.layoutManager = LinearLayoutManager(this@MainActivity)
                            this.adapter = adapter
                        }
                    }
                    is Resource.Error -> {
                        Log.v("Main_Activity", "Response Error")
                        binding.progressBar.visibility = View.GONE
                        binding.networkError.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}