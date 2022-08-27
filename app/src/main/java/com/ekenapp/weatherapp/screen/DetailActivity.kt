package com.ekenapp.weatherapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenapp.weatherapp.R
import com.ekenapp.weatherapp.adapters.ForecastAdapter
import com.ekenapp.weatherapp.api.Repository
import com.ekenapp.weatherapp.database.City
import com.ekenapp.weatherapp.database.CityDao
import com.ekenapp.weatherapp.database.DatabaseInstance
import com.ekenapp.weatherapp.databinding.ActivityDetailBinding
import com.ekenapp.weatherapp.utils.DatabaseUtil
import com.ekenapp.weatherapp.viewmodels.ForecastViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private var city: City? = null
    private var isInDatabase = false
    private lateinit var databaseInterface : CityDao
    private lateinit var databaseUtil : DatabaseUtil
    private lateinit var binding: ActivityDetailBinding
    private lateinit var forecastAdapter: ForecastAdapter
    private val forecastViewModel: ForecastViewModel by viewModels {
        val repository = Repository()
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ForecastViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        city = intent.getSerializableExtra("city") as City?

        if(city == null) {
            Toast.makeText(this, "Could not find the weather data!", Toast.LENGTH_LONG).show()
            finish()
        }

        forecastAdapter = ForecastAdapter(city!!.cityName)

        supportActionBar!!.title = city!!.cityName

        databaseInterface = DatabaseInstance.getCityDatabaseDao(this)
        databaseUtil = DatabaseUtil(databaseInterface)

        setupRecyclerView()
        setupObserver()

        forecastViewModel.getForecastResults(city!!.cityKey)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.detail_activity_menu, menu)
        lifecycleScope.launch(Dispatchers.IO) {
            if(databaseUtil.isItInTheDatabase(city!!.cityKey)) {
                isInDatabase = true
                withContext(Dispatchers.Main) {
                    menu!!.getItem(0).icon = ContextCompat.getDrawable(applicationContext, R.drawable.favorite_filled_icon)
                }
            } else {
                isInDatabase = false
                withContext(Dispatchers.Main) {
                    menu!!.getItem(0).icon = ContextCompat.getDrawable(applicationContext, R.drawable.favorite_empty_icon)
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favoriteIcon -> {
                if(isInDatabase) {
                    item.icon = ContextCompat.getDrawable(applicationContext, R.drawable.favorite_empty_icon)
                    lifecycleScope.launch(Dispatchers.IO) {
                        databaseUtil.removeFromDatabase(city!!.cityKey)
                    }
                    isInDatabase = false
                } else {
                    item.icon = ContextCompat.getDrawable(applicationContext, R.drawable.favorite_filled_icon)
                    lifecycleScope.launch(Dispatchers.IO) {
                        databaseUtil.addToTheDatabase(city!!)
                    }
                    isInDatabase = true
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        binding.forecastListView.layoutManager = LinearLayoutManager(this)

        binding.forecastListView.adapter = forecastAdapter
    }

    private fun setupObserver() {
        forecastViewModel.forecastResults.observe(this, Observer {
            it?.let { forecastAdapter.forecast = it }
        })
    }
}