package io.loperilla.jokeapp.presentation.screens.jokeresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.loperilla.jokeapp.domain.model.joke.JokeModel
import io.loperilla.jokeapp.presentation.joke
import io.loperilla.jokeapp.presentation.theme.JokePreview

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */

@Composable
fun JokeResultScreen(
    state: JokeResultState,
    onEvent: (event: JokeResultEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
            if (state.jokeModels.size == 1) {
                JokeCard(
                    jokeModel = state.jokeModels.first(),
                    modifier = Modifier
                        .fillMaxSize()
                )
            } else {
                JokeList(
                    jokeModels = state.jokeModels,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun JokeList(
    jokeModels: List<JokeModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(8.dp)
    ) {
        items(jokeModels.size, key = { jokeModels[it].id}) {
            JokeCard(
                jokeModel = jokeModels[it]
            )
        }
    }
}

@Composable
private fun JokeCard(
    jokeModel: JokeModel,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = jokeModel.setup,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = jokeModel.delivery,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@JokePreview
@Composable
private fun JokeResultLoadingPreview() {
    JokeResultScreen(
        state = JokeResultState(
            isLoading = true
        ),
        onEvent = {},
        modifier = Modifier
            .fillMaxSize()
    )
}

@JokePreview
@Composable
private fun JokeResultSingleJokePreview() {
    JokeResultScreen(
        state = JokeResultState(
            isLoading = false,
            jokeModels = listOf(joke)
        ),
        onEvent = {},
        modifier = Modifier
            .fillMaxSize()
    )
}@JokePreview
@Composable
private fun JokeResultJokeListPreview() {
    JokeResultScreen(
        state = JokeResultState(
            isLoading = false,
            jokeModels = listOf(
                joke.copy(id = 1),
                joke.copy(id = 2),
                joke.copy(id = 3)
            )
        ),
        onEvent = {},
        modifier = Modifier
            .fillMaxSize()
    )
}