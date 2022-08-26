package com.ekenapp.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ekenapp.weatherapp.R
import com.ekenapp.weatherapp.api.model.SearchRecommendation
import com.ekenapp.weatherapp.utils.SearchItemClick

class SearchAdapter(private val searchItemClick: SearchItemClick):
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var searchResults: List<SearchRecommendation>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val completeName = if(searchResults!![position].country != null && !searchResults!![position].country!!.name.isNullOrEmpty()) {
            searchResults!![position].name + ", " + searchResults!![position].country!!.name
        } else {
            searchResults!![position].name!!
        }
        holder.cityNameView.text = completeName

        holder.container.setOnClickListener {
            searchItemClick.onSearchItemClick(searchResults!![position].name!!)
        }
    }

    override fun getItemCount(): Int {
        return searchResults?.size ?: 0
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityNameView: TextView = view.findViewById(R.id.cityNameView)
        val container: ConstraintLayout = view.findViewById(R.id.container)
    }
}