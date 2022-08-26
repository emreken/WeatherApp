package com.ekenapp.weatherapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenapp.weatherapp.adapters.SearchAdapter
import com.ekenapp.weatherapp.api.Repository
import com.ekenapp.weatherapp.databinding.ActivityMainBinding
import com.ekenapp.weatherapp.screen.DetailActivity
import com.ekenapp.weatherapp.utils.SearchItemClick
import com.ekenapp.weatherapp.viewmodels.SearchViewModel


class MainActivity : AppCompatActivity(), SearchItemClick {

    private lateinit var binding: ActivityMainBinding
    private val searchViewModel: SearchViewModel by viewModels {
        val repository = Repository()
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SearchViewModel(repository) as T
            }
        }
    }
    private val searchAdapter = SearchAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSearchView()
        setupSearchRecyclerView()
        setupObserver()
    }

    private fun setupSearchView() {
        binding.searchView.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val userText = p0.toString()
                if(userText.isNotEmpty()){
                    searchViewModel.getSearchResults(userText)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun setupSearchRecyclerView() {
        binding.searchResultListView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        )
        binding.searchResultListView.addItemDecoration(dividerItemDecoration)

        binding.searchResultListView.adapter = searchAdapter
    }

    private fun setupObserver() {
        searchViewModel.searchResults.observe(this, Observer {
            it?.let { searchAdapter.searchResults = it }
        })
    }

    override fun onSearchItemClick(name: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("city_name", name)
        startActivity(intent)
    }
}