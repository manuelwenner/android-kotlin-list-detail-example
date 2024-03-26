package de.manuelwenner.locations

import android.widget.ListAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class LocationAdapter(context: Context, locations: List<Location>) :
    ArrayAdapter<Location>(context, 0, locations) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView

        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(
                R.layout.list_item_location,
                parent,
                false
            )
        }

        val currentLocation = getItem(position)

        val textViewName = listItemView!!.findViewById<TextView>(R.id.tvName)
        val textViewDuration = listItemView.findViewById<TextView>(R.id.tvDuration)
        val textViewDistance = listItemView.findViewById<TextView>(R.id.tvDistance)
        val textViewDate = listItemView.findViewById<TextView>(R.id.tvDate)

        textViewName.text = currentLocation?.name
        textViewDuration.text = "Duration: ${currentLocation?.duration}"
        textViewDistance.text = "Distance: ${currentLocation?.distance}"
        textViewDate.text = currentLocation?.date

        return listItemView
    }
}
