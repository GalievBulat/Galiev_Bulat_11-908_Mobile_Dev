package com.kakadurf.hw_sem2.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kakadurf.hw_sem2.R
import com.kakadurf.hw_sem2.presentation.models.SpotDTO
import com.kakadurf.hw_sem2.presentation.models.TemperatureData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_spot_element.*

class SpotAdapter(private val itemList : List<SpotDTO>,
                  private val onClickListener: (Int)-> Unit) : RecyclerView.Adapter<SpotHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotHolder {
       return SpotHolder.builder(
           parent
       ).also {
           it.onClickListener = onClickListener }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SpotHolder, position: Int) {
        holder.bind(itemList[position])
    }

}
class SpotHolder( override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    var onClickListener: ((Int)-> Unit)? = null
    companion object{
        fun builder(parent: ViewGroup) : SpotHolder {
            return SpotHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_spot_element,
                    parent,
                    false
                )
            )
        }
    }
    fun bind(spot: SpotDTO){
        tv_spot_name.text = spot.name
        tv_tempr.text = spot.tempr.toString()
        TemperatureData.create(spot.tempr)?.colour?.let{
            Log.d("hi",it.toString())
            Log.d("hi",R.color.weather_normal.toString())
            tv_tempr.setTextColor(it)
        }
        itemView.setOnClickListener { onClickListener?.let { it1 -> it1(spot.id) } }
    }

}