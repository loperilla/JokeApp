package io.loperilla.jokeapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp
 * Created By Manuel Lopera on 12/10/24 at 14:15
 * All rights reserved 2024
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainCoroutineExtension(
    val testDispatcher: TestDispatcher = StandardTestDispatcher()
): BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}