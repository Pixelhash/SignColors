package de.codehat.signcolors.translation

import de.codehat.signcolors.SignColorsPlugin
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

private const val TRANSLATION_DIR = "translations"

class TranslationManager(private val plugin: SignColorsPlugin) {

    companion object {
        private val LANGUAGE_CODES = listOf("de_DE", "en_US")
    }

    fun load() {

    }
}
