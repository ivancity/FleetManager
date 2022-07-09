package com.ivan.m.fleetmanager.presentation.vehicle_history.components

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun DatePickerComponent(
    context: Context,
    onValueChanged: (String) -> Unit
) {
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember {
        mutableStateOf(
            LocalDate
                .now()
                .format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
                )
        )
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val monthFormatted = String.format("%02d", month + 1)
            val dayFormatted = String.format("%02d", dayOfMonth)
            date.value = "$year-$monthFormatted-$dayFormatted"
            onValueChanged(date.value)
        }, year, month, day
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Selected Date:")
        Text(text = date.value)
        IconButton(onClick = { datePickerDialog.show() }) {
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = "calendarIcon"
            )
        }
    }
}

@Preview
@Composable
fun DatePickerComponentPreview() {
    DatePickerComponent(LocalContext.current) { _ -> }
}