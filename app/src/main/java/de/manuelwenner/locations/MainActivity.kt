package de.manuelwenner.locations

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var locationListView: ListView
    private lateinit var locations: List<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        locationListView = findViewById(R.id.locationListView)

        // Initialize mock data
        locations = listOf(
            Location(1, "Location 1", "2 hours", "50 km", "2024-03-26"),
            Location(2, "Location 2", "1.5 hours", "30 km", "2024-03-25"),
            Location(3, "Location 3", "3 hours", "80 km", "2024-03-24")
        )

        // Set up adapter
        val adapter = LocationAdapter(this, locations)

        locationListView.adapter = adapter

        // Handle item click
        locationListView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedLocation = locations[position]
                val intent = Intent(this, LocationDetailActivity::class.java)
                intent.putExtra("location", selectedLocation)
                startActivity(intent)
            }
    }
}