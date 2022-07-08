package com.ivan.m.fleetmanager.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppBar(
    appBarState: AppBarState,
    onNavigateUp: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = appBarState.title
            )
        },
        navigationIcon = if (appBarState.showBackButton) {
            {
                IconButton(onClick = { onNavigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        actions = {
            appBarState.actions?.invoke(this)
        }
    )
}