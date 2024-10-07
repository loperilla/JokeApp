package io.loperilla.jokeapp.presentation.screens.jokeresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.loperilla.jokeapp.domain.model.FormData

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */

@Composable
fun JokeResultScreen(
    state: FormData,
    onEvent: (event: JokeResultEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = "Categories: ${state.categories}")
        Text(text = "Language: ${state.language}")
        Text(text = "Flags:${state.flags}")
        Text(text = "Amount: ${state.amount}")
        Text(text = "Joke Result Screen")
        Button(onClick = {
            onEvent(JokeResultEvent.BackToWelcome)
        }) {
            Text("Back to Welcome")
        }
    }
}