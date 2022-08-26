package com.ekenapp.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ekenapp.weatherapp.R
import com.ekenapp.weatherapp.database.City
import com.ekenapp.weatherapp.utils.SearchItemClick

class CityAdapter(private val itemClick: SearchItemClick):
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    var cityResults: List<City>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.cityItemName.text = cityResults!![position].name

        holder.container.setOnClickListener {
            itemClick.onSearchItemClick(cityResults!![position].name)
        }
    }

    override fun getItemCount(): Int {
        return cityResults?.size ?: 0
    }

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityItemName: TextView = view.findViewById(R.id.cityItemName)
        val container: ConstraintLayout = view.findViewById(R.id.container)
    }
}