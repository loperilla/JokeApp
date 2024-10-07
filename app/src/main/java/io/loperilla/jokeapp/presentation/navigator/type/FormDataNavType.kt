package io.loperilla.jokeapp.presentation.navigator.type

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import io.loperilla.jokeapp.domain.model.FormData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.navigator.type
 * Created By Manuel Lopera on 7/10/24 at 20:13
 * All rights reserved 2024
 */
val FormDataNavType = object : NavType<FormData>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): FormData? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): FormData {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: FormData): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: FormData) {
        bundle.putString(key, Json.encodeToString(value))
    }
}