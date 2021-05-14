package de.codehat.signcolors

import java.nio.file.Files
import java.nio.file.Path
import java.util.*

internal fun Properties.load(path: Path) {
    return load(Files.newInputStream(path))
}

typealias PropertiesKeys = List<String>

/**
 * Returns a list of keys that are missing from the given [properties]
 * and a list of keys that are only contained in [properties].
 */
internal fun Properties.diff(properties: Properties): Pair<PropertiesKeys, PropertiesKeys> {
    val newKeys = mutableListOf<String>()
    val consideredKeys = mutableListOf<String>()

    stringPropertyNames().forEach {
        consideredKeys.add(it)
        if (!properties.contains(it)) {
            newKeys.add(it)
        }
    }

    val oldKeys = mutableListOf<String>().apply {
        addAll(properties.stringPropertyNames())
        removeAll(consideredKeys)
    }

    return Pair(newKeys, oldKeys)
}
