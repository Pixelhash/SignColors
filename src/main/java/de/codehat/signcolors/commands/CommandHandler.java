/*
 * Copyright (c) 2016 CodeHat.
 * This file is part of 'SignColors' and is licensed under GPLv3.
 */

package de.codehat.signcolors.commands;

import de.codehat.signcolors.SignColors;
import de.codehat.signcolors.languages.LanguageLoader;
import de.codehat.signcolors.util.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {

    private static HashMap<String, BaseCommand> registry = new HashMap<>();

    private SignColors plugin;
    private LanguageLoader lang;

    public CommandHandler(SignColors plugin, LanguageLoader lang) {
        this.plugin = plugin;
        this.lang = lang;
    }

    public void registerNewCommand(String name, BaseCommand cmd) {
        registry.put(name, cmd);
    }

    public boolean exists(String name) {
        return registry.containsKey(name);
    }

    public BaseCommand getExecutor(String name) {
        return registry.get(name);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Info command
        if (args.length == 0) {
            if (!sender.hasPermission("signcolors.info")) {
                Message.sendLogoMsg(sender, lang.getLang("nocmd"));
                return true;
            }
            Message.sendMsg(sender, "&6+--------------------[&3SignColors&6]--------------------+");
            Message.sendMsg(sender, "&6" + lang.getLang("sciauthor") + " &aCodeHat");
            Message.sendMsg(sender, "&6Version:&a " + this.plugin.getDescription().getVersion());
            Message.sendMsg(sender, "&6" + lang.getLang("scicmd") + " &a" + lang.getLang("scicmdh"));
            Message.sendMsg(sender, "&6+--------------------------------------------------+");
            return true;
        }
        //Rest of commands
        if (exists(args[0])) {
            getExecutor(args[0]).onCommand(sender, cmd, label, args);
            return true;
        } else {
            Message.sendLogoMsg(sender, lang.getLang("uncmd"));
            Message.sendLogoMsg(sender, lang.getLang("uncmdh"));
            return true;
        }
    }
}