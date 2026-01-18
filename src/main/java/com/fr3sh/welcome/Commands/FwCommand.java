package com.fr3sh.welcome.Commands;

import com.fr3sh.welcome.Main;
import com.fr3sh.welcome.Utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class FwCommand implements CommandExecutor, TabCompleter {

    private final Main plugin;

    public FwCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("fr3sh.reload")) {
                plugin.reloadConfig();
                String reloadMsg = plugin.getConfig().getString("messages.reload", "<green>Configuration reloaded!");
                sender.sendMessage(ChatUtil.format(reloadMsg));
            } else {
                String noPermMsg = plugin.getConfig().getString("messages.no-permission", "<red>You don't have permission!");
                sender.sendMessage(ChatUtil.format(noPermMsg));
            }
            return true;
        }

        sender.sendMessage(ChatUtil.format("<red>Usage: /fw reload"));
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        // If the player does not have permission, return an empty list immediately.
        if (!sender.hasPermission("fr3sh.reload")) {
            return Collections.emptyList();
        }

        // If they have permission, suggest "reload" as the first argument.
        if (args.length == 1) {
            return Collections.singletonList("reload");
        }

        // For any other arguments, suggest nothing.
        return Collections.emptyList();
    }
}
