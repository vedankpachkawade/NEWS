package com.example.news.NEWS

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.news.NEWS.ui.main.AdapterNews
import com.example.news.NEWS.utils.di.MyResponse
import com.example.news.NEWS.viewmodel.MainViewModel
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding ?= null
    private val binding get() = _binding

    private val viewModel:MainViewModel by viewModels()

    @Inject
    lateinit var newsAdapter: AdapterNews
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupViews()
        observeNewsData()
        viewModel.getAllNotes()


    }

    private fun observeNewsData()
    {
        viewModel.newsData.observe(this@MainActivity){response ->
            when(response.status)
            {
                MyResponse.Status.LOADING -> {
                    binding?.loading?.visibility = View.VISIBLE
                }
                MyResponse.Status.SUCCESS -> {
                    binding?.loading?.visibility = View.GONE
                    response?.data?.articles?.let { newsAdapter.submitData(it) }
                }
                MyResponse.Status.ERROR -> {
                    binding?.loading?.visibility = View.GONE
                    Toast.makeText(this@MainActivity , response.message , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupViews() {
        binding?.recyclerview?.apply{
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            adapter =  newsAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}