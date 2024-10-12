package io.loperilla.jokeapp.presentation.screens.jokeform

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.loperilla.jokeapp.domain.model.Language
import io.loperilla.jokeapp.presentation.theme.JokePreview

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeFormScreen(
    state: JokeFormState,
    onEvent: (JokeFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Joke Form") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(JokeFormEvent.BackToWelcome)
                        },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
        ) {
            LanguageSelector(
                state,
                onEvent = onEvent,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            CategorySelector(
                state = state,
                onEvent = onEvent,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            FlagSelector(
                state = state,
                onEvent = onEvent,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            JokeAmountSelector(
                amount = state.jokeAmount,
                onEvent = onEvent,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            Spacer(
                Modifier
                    .weight(1f)
            )
            Button(
                enabled = state.languageSelected != null,
                onClick = {
                    onEvent(JokeFormEvent.NavigateToJokeResult)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }
}

@Composable
fun FlagSelector(
    state: JokeFormState,
    onEvent: (JokeFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text("Selecciona una capa más fina de filtrado")
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(state.flagList.size) {
                ItemChip(
                    text = state.flagList[it].name,
                    isSelected = state.flagsSelected.contains(state.flagList[it]),
                    onClick = {
                        onEvent(JokeFormEvent.SelectFlag(state.flagList[it]))
                    }
                )
            }
        }
    }
}

@Composable
fun LanguageSelector(
    state: JokeFormState,
    onEvent: (JokeFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val rotationState by animateFloatAsState(
        targetValue = if (state.languageSelectorVisibility) 180f else 0f, label = ""
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Selecciona el idioma del chiste: ${state.languageSelected?.name.orEmpty()}",
            )
            IconButton(onClick = { onEvent(JokeFormEvent.ChangeChipVisibility) }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Icon show chips",
                    modifier = Modifier
                        .rotate(rotationState)
                )
            }
        }
        DropdownMenu(
            expanded = state.languageSelectorVisibility,
            onDismissRequest = {
                onEvent(JokeFormEvent.HideLanguageSelector)
            }
        ) {
            state.languageList.forEach { language ->
                DropdownMenuItem(
                    text = { Text(text = language.name) },
                    onClick = {
                        onEvent(JokeFormEvent.SelectLanguage(language))
                    }
                )
            }
        }
    }
}

@Composable
fun CategorySelector(
    state: JokeFormState,
    onEvent: (JokeFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text("Selecciona sobre qué quieres que vaya tu chiste")
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(state.categoryList.size) {
                ItemChip(
                    text = state.categoryList[it].resolvedName,
                    isSelected = state.categoriesSelected.contains(state.categoryList[it]),
                    onClick = {
                        onEvent(JokeFormEvent.SelectCategory(state.categoryList[it]))
                    }
                )
            }
        }
    }
}

@Composable
fun JokeAmountSelector(
    amount: Int,
    onEvent: (JokeFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(
                enabled = amount != 1,
                onClick = {
                    onEvent(JokeFormEvent.MinusAmount)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Remove,
                    contentDescription = null
                )
            }
            Text(
                text = "$amount",
                fontSize = 24.sp
            )

            IconButton(
                enabled = amount != 10,
                onClick = {
                    onEvent(JokeFormEvent.PlusAmount)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ItemChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    iconSelected: ImageVector = Icons.Filled.Done,
    modifier: Modifier = Modifier
) {
    val color = FilterChipDefaults.filterChipColors(
        selectedContainerColor = MaterialTheme.colorScheme.primary,
        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
    )
    FilterChip(
        onClick = {
            onClick()
        },
        label = { Text(text) },
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = iconSelected,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            } else {
                null
            }
        },
        colors = color,
        selected = isSelected,
        modifier = modifier
    )
}

@JokePreview
@Composable
private fun JokeFormPreview() {
    JokeFormScreen(
        state = JokeFormState(
            languageSelectorVisibility = true,
            languageSelected = Language("es", "Español")
        ),
        onEvent = {}
    )
}