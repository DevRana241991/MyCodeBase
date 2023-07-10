package com.example.fitpeotasksample.ui.home.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitpeotasksample.data.model.GetServerData
import com.example.fitpeotasksample.databinding.ActivityHomeBinding
import com.example.fitpeotasksample.ui.home.adapters.ListDataAdapter
import com.example.fitpeotasksample.ui.home.model.UIState
import com.example.fitpeotasksample.ui.home.viewmodel.HomeViewModel
import com.example.fitpeotasksample.utils.gone
import com.example.fitpeotasksample.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: ListDataAdapter
    private val homeViewModel: HomeViewModel? by viewModels()
    private var listData=ArrayList<GetServerData>()

    companion object{
        var selectedData:GetServerData?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        observeFlowData()
        homeViewModel?.loadUserData()
    }

    private fun observeFlowData() {
        lifecycleScope.launchWhenStarted {
            homeViewModel?.userFlow?.collect { state ->
                when (state) {
                    is UIState.Loading -> {
                        showProgress()
                    }
                    is UIState.Success -> {
                        state.data?.let { updateUI(it) } ?: showError()
                        hideProgress()
                    }
                    is UIState.Error -> {
                        hideProgress()
                        showError()
                    }
                }
            }
        }
    }

    private fun showProgress() {
        binding.progressBar.visible()
        binding.rvData.gone()
    }

    private fun hideProgress() {
        binding.progressBar.gone()
        binding.rvData.visible()
    }

    private fun showError() {
        binding.progressBar.gone()
        binding.rvData.gone()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateUI(updatedListData: ArrayList<GetServerData>) {

        listData.clear()
        listData.addAll(updatedListData)
        adapter.notifyDataSetChanged()

    }

    private fun setAdapter(){
        adapter= ListDataAdapter(listData,this)
        binding.rvData.adapter=adapter
    }
}