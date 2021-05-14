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
package de.codehat.signcolors.koin

import de.codehat.signcolors.SignColorsPlugin
import de.codehat.signcolors.api.SignColors
import de.codehat.signcolors.listener.PlayerListener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module

internal fun signColorsPluginModule(plugin: SignColorsPlugin): Module {
    return module {
        single { plugin } binds arrayOf(SignColors::class, JavaPlugin::class, Plugin::class)
    }
}

internal val signColorsModule = module {
    single { PlayerListener(get()) }
}
