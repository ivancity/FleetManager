package com.ivan.m.fleetmanager.domain.preferences

interface Preferences {
    fun save(apiKey: String)
    fun loadApiKey(): String

    companion object {
        const val KEY_API_KEY = "fleet_api_key"
    }
}