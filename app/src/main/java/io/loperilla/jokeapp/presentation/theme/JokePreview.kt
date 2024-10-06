package io.loperilla.jokeapp.presentation.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.theme
 * Created By Manuel Lopera on 5/10/24 at 12:32
 * All rights reserved 2024
 */
@Preview(
    name = "PIXEL_XL API 33 NIGHT",
    locale = "es",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4_XL
)

@Preview(
    name = "PIXEL_XL API 33 LIGHT",
    locale = "es",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_4_XL
)
annotation class JokePreview
