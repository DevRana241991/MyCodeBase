package com.example.fitpeotasksample.ui.detail.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.toolbox.ImageLoader
import com.example.fitpeotasksample.R
import com.example.fitpeotasksample.databinding.ActivityDetailBinding
import com.example.fitpeotasksample.ui.home.activity.HomeActivity
import com.example.fitpeotasksample.utils.gone
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun setData(){

        val imageLoader = CustomVolleyRequest.getInstance(this)?.imageLoader
        imageLoader?.get(HomeActivity.selectedData?.url, ImageLoader.getImageListener(binding.ivMainImage, R.drawable.ic_launcher_foreground, android.R.drawable
            .ic_dialog_alert))
        binding.ivMainImage.setImageUrl(HomeActivity.selectedData?.url, imageLoader)

        binding.tvTitle.text=HomeActivity.selectedData?.title
        binding.tvDescription.gone()
    }



}