package de.codehat.signcolors

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*
import java.util.*

class PropertiesExtensionsTest {
    @Test
    fun `does diff return two empty lists with empty properties`() {
        val expected = Pair(mutableListOf<String>(), mutableListOf<String>())
        val actual = Properties().diff(Properties())
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `does diff return the missing keys to add`() {
        val expected = Pair(mutableListOf("key1", "key2"), mutableListOf<String>())
        val actual = Properties().apply {
            put("key1", "")
            put("key2", "")
        }.diff(Properties())
        assertThat(actual).isEqualTo(expected)
    }
}
