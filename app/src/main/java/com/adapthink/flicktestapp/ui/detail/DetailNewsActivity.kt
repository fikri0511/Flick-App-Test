package com.adapthink.flicktestapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.adapthink.flicktestapp.R
import com.adapthink.flicktestapp.core.domain.model.News
import com.adapthink.flicktestapp.core.util.ConstGlobal.INTENT_DETAIL
import com.adapthink.flicktestapp.core.util.ConstGlobal.convertWaktuNews
import com.adapthink.flicktestapp.databinding.ActivityDetailNewsBinding
import com.bumptech.glide.Glide


class DetailNewsActivity : AppCompatActivity() {
    private lateinit var data: News
    private lateinit var binding: ActivityDetailNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {

            data = intent.getParcelableExtra(INTENT_DETAIL)!!

            binding.judulDetailNews.text = data.title
            binding.tvTime.text = convertWaktuNews(data.publishedAt)

            binding.tvIsiberita.text = data.description

            Glide.with(this)
                .load(data.urlToImage)
                .into(binding.imageDetailnews)

            binding.btnLinkNews.setOnClickListener {
                val uri: Uri =
                    Uri.parse(data.url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.equals(R.id.home)) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}