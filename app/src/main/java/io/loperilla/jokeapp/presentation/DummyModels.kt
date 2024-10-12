package io.loperilla.jokeapp.presentation

import io.loperilla.jokeapp.domain.model.joke.JokeFlags
import io.loperilla.jokeapp.domain.model.joke.JokeModel

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation
 * Created By Manuel Lopera on 12/10/24 at 11:17
 * All rights reserved 2024
 */

val joke = JokeModel(
    id = 1,
    category = "Programming",
    flags = JokeFlags(
        nsfw = false,
        religious = false,
        political = false,
        racist = false,
        sexist = false,
        explicit = false
    ),
    setup = "What do you call a fake noodle?",
    delivery = "An impasta.",
    type = "single",
    lang = "en",
    safe = true,
)