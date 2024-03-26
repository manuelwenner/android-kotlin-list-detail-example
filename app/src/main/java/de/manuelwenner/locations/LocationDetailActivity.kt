package de.manuelwenner.locations

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LocationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)
        var location: Location? = null

        intent.getParcelableExtra<Location>("location")?.let {
            location = it
        }

        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = location?.name
                ?: "Location Detail"
        }

        findViewById<TextView>(R.id.tvDurationHeader).text = getString(R.string.duration_placeholder, location?.duration)
        findViewById<TextView>(R.id.tvDistanceHeader).text = getString(R.string.distance_placeholder, location?.distance)
        findViewById<TextView>(R.id.tvDateHeader).text = location?.date
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
