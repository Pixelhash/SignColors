/**
 * SignColors is a Spigot plugin that allows players adding colors/formatting to signs.
 * Copyright (C) 2021 Marc-Niclas Harm.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.codehat.signcolors

import de.codehat.signcolors.api.SignColors
import de.codehat.signcolors.api.SignColorsApi
import de.codehat.signcolors.koin.SignColorsKoinContext
import de.codehat.signcolors.koin.signColorsModule
import de.codehat.signcolors.koin.signColorsPluginModule
import de.codehat.signcolors.listener.PlayerListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class SignColorsPlugin : JavaPlugin(), SignColors, KoinComponent {

    companion object {
        private lateinit var instance: SignColorsPlugin

        @JvmStatic
        fun getInstance() = instance
    }

    override fun onEnable() {
        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
            logger.severe("Unable to create data folder! Disabling plugin...")
            Bukkit.getPluginManager().disablePlugin(this)
            return
        }

        instance = this
        SignColorsApi.signColors = this

        koinApplication {
            modules(signColorsPluginModule(this@SignColorsPlugin), signColorsModule)
        }.apply {
            SignColorsKoinContext.koinApp = this
            startKoin(this)
        }

        registerListeners()

        logger.info("v${description.version} enabled.")
    }

    override fun onDisable() {
        with(SignColorsKoinContext) {
            koinApp?.close()
            koinApp = null
        }
        Bukkit.getScheduler().cancelTasks(this)

        logger.info("v${description.version} disabled.")
    }

    private fun registerListeners() {
        val playerListener by inject<PlayerListener>()
        Bukkit.getPluginManager().registerEvents(playerListener, this)
    }

}
