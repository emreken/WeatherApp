package com.ekenapp.weatherapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.ekenapp.weatherapp.R
import com.ekenapp.weatherapp.database.CityDao
import com.ekenapp.weatherapp.database.DatabaseInstance
import com.ekenapp.weatherapp.utils.DatabaseUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private var cityName:String? = null
    private var isInDatabase = false
    private lateinit var databaseInterface : CityDao
    private lateinit var databaseUtil : DatabaseUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        cityName = intent.getStringExtra("city_name")

        databaseInterface = DatabaseInstance.getCityDatabaseDao(this)
        databaseUtil = DatabaseUtil(databaseInterface)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.detail_activity_menu, menu)
        lifecycleScope.launch(Dispatchers.IO) {
            if(databaseUtil.isItInTheDatabase(cityName!!)) {
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
                        databaseUtil.removeFromDatabase(cityName!!)
                    }
                    isInDatabase = false
                } else {
                    item.icon = ContextCompat.getDrawable(applicationContext, R.drawable.favorite_filled_icon)
                    lifecycleScope.launch(Dispatchers.IO) {
                        databaseUtil.addToTheDatabase(cityName!!)
                    }
                    isInDatabase = true
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}