package com.ivan.m.fleetmanager.presentation.latest_data_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LatestDataItem(
    id: String,
    plate: String,
    name: String,
    address: String,
    speed: String,
    timestamp: String,
    onClick: (id: String, plate: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable { onClick(id, plate) },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$plate / $name",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = if(speed != "0") { "$speed km/h" } else { "-" },
                    style = MaterialTheme.typography.h6,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = address,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = timestamp,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }

    }
}

@Preview
@Composable
fun LatestDataItemPreview() {
    LatestDataItem(
        id = "654",
        plate = "XY654",
        name = "Bob Some very long name here that continues",
        address = "Some Address that is also very very long some what",
        speed = "20",
        timestamp = "1m 22s ago"
    ) { _, _ -> }
}
