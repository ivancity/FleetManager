package com.ivan.m.fleetmanager.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class AppBarState(
    val title: String = "",
    val showBackButton: Boolean = false,
    val actions: (@Composable RowScope.() -> Unit)? = null
)
