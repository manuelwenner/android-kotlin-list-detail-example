package de.manuelwenner.locations

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {

    val locations = listOf(
        Location(1, "Location 1", "2 hours", "50 km", "2024-03-26"),
        Location(2, "Location 2", "1.5 hours", "30 km", "2024-03-25"),
        Location(3, "Location 3", "3 hours", "80 km", "2024-03-24")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_compose)

        findViewById<ComposeView>(R.id.composeView)?.let {
            it.setContent {
                LocationList(locations = locations) { selectedLocation ->
                    Log.d("MW DEBUG", selectedLocation.toString())
                    startActivity(
                        Intent(this@MainActivity, LocationDetailActivity::class.java).apply {
                            putExtra("location", selectedLocation)
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun LocationList(locations: List<Location>, onItemClick: (Location) -> Unit) {
        LazyColumn {
            items(locations) { location ->
                LocationListItem(location = location, onItemClick = onItemClick)
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }

    @Composable
    fun LocationListItem(location: Location, onItemClick: (Location) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onItemClick(location) })
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = location.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp

                )
                Text(
                    text = "Duration: ${location.duration}",
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
                Text(
                    text = "Distance: ${location.distance}",
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = location.date,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Top)
            )
        }
    }
}