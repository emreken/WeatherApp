package com.ekenapp.weatherapp.adapters

import com.ekenapp.weatherapp.api.model.Forecast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ekenapp.weatherapp.R

class ForecastAdapter(private val cityName: String) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    var forecast: Forecast? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        var tempStrArr = forecast!!.dailyForecasts!![position].date!!.split("T")
        holder.dateView.text = tempStrArr[0]
        holder.nameView.text = cityName
        val averageTemperature = (forecast!!.dailyForecasts!![position].temperature!!.minimum!!.value!! +
                forecast!!.dailyForecasts!![position].temperature!!.maximum!!.value!!) / 2.0
        val averageTempStr = averageTemperature.toString() + " °" +  forecast!!.dailyForecasts!![position].temperature!!.minimum!!.unit
        holder.temperatureView.text = averageTempStr
        tempStrArr = forecast!!.dailyForecasts!![position].sun!!.rise!!.split("T")
        holder.sunRiseView.text = tempStrArr[1].substring(0, 5)
        tempStrArr = forecast!!.dailyForecasts!![position].sun!!.set!!.split("T")
        holder.sunSetView.text = tempStrArr[1].substring(0, 5)
        var tempStr = forecast!!.dailyForecasts!![position].temperature!!.minimum!!.value!!.toString() +
                " °" +  forecast!!.dailyForecasts!![position].temperature!!.minimum!!.unit
        holder.temperatureMinView.text = tempStr
        tempStr = forecast!!.dailyForecasts!![position].temperature!!.maximum!!.value!!.toString() +
                " °" +  forecast!!.dailyForecasts!![position].temperature!!.maximum!!.unit
        holder.temperatureMaxView.text = tempStr
        holder.hourOfSunView.text = forecast!!.dailyForecasts!![position].hoursOfSun!!.toString()
        val windSpeedStr = forecast!!.dailyForecasts!![position].day!!.wind!!.speed!!.value.toString() +
                " " + forecast!!.dailyForecasts!![position].day!!.wind!!.speed!!.unit
        holder.windSpeedView.text = windSpeedStr
        holder.descriptionView.text = forecast!!.dailyForecasts!![position].day!!.longPhrase
    }

    override fun getItemCount(): Int {
        return forecast?.dailyForecasts?.size ?: 0
    }

    inner class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateView: TextView = view.findViewById(R.id.dateView)
        val nameView: TextView = view.findViewById(R.id.nameView)
        val temperatureView: TextView = view.findViewById(R.id.temperatureView)
        val sunRiseView: TextView = view.findViewById(R.id.sunRiseView)
        val sunSetView: TextView = view.findViewById(R.id.sunSetView)
        val temperatureMinView: TextView = view.findViewById(R.id.temperatureMinView)
        val temperatureMaxView: TextView = view.findViewById(R.id.temperatureMaxView)
        val hourOfSunView: TextView = view.findViewById(R.id.hourOfSunView)
        val windSpeedView: TextView = view.findViewById(R.id.windSpeedView)
        val descriptionView: TextView = view.findViewById(R.id.descriptionView)
    }
}