package com.ivan.m.fleetmanager.domain.use_case.preferences

import android.content.SharedPreferences
import com.ivan.m.fleetmanager.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
) : Preferences {
    override fun save(apiKey: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_API_KEY, apiKey)
            .apply()
    }

    override fun loadApiKey(): String {
        return sharedPref.getString(Preferences.KEY_API_KEY, "") ?: ""
    }

}