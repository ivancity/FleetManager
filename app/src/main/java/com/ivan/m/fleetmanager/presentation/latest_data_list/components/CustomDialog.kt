package com.ivan.m.fleetmanager.presentation.latest_data_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomDialog(
    value: String,
    setShowDialog: (Boolean) -> Unit,
    setValue: (String) -> Unit
) {

    var textValue by remember { mutableStateOf(value) }

    AlertDialog(
        onDismissRequest = {
            setShowDialog(false)
        },
        title = {
            Text(text = "Title")
        },
        text = {
            Column() {
                TextField(
                    value = textValue,
                    onValueChange = { textValue = it }
                )
            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        setValue(textValue)
                        setShowDialog(false)
                    }
                ) {
                    Text("Done")
                }
            }
        }
    )
}