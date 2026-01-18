package com.fr3sh.welcome;

import com.fr3sh.welcome.Commands.FwCommand;
import com.fr3sh.welcome.Listeners.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if (getCommand("fw") != null) {
            FwCommand fwCommand = new FwCommand(this);
            getCommand("fw").setExecutor(fwCommand);
            getCommand("fw").setTabCompleter(fwCommand);
        }

        getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);

        getLogger().info("================================");
        getLogger().info(" FR3SH-WELCOME ENABLED ");
        getLogger().info(" Version: 1.0");
        getLogger().info("================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("================================");
        getLogger().info(" FR3SH-WELCOME DISABLED");
        getLogger().info(" Version: 1.0");
        getLogger().info("================================");
    }
}
