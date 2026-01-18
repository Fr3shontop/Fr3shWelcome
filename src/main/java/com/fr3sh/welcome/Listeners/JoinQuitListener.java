package com.fr3sh.welcome.Listeners;

import com.fr3sh.welcome.Main;
import com.fr3sh.welcome.Utils.ChatUtil;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.Duration;

public class JoinQuitListener implements Listener {

    private final Main plugin;

    public JoinQuitListener(Main plugin) {
        this.plugin = plugin;
    }

    private Component formatMessage(String message, String playerName) {
        if (message == null) return Component.empty();
        return ChatUtil.format(message.replace("%player%", playerName));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (plugin.getConfig().getBoolean("join.sound.enabled")) {
            playSound("join.sound");
        }

        if (plugin.getConfig().getBoolean("join.chat.enabled")) {
            String message = plugin.getConfig().getString("join.chat.message");
            event.joinMessage(formatMessage(message, playerName));
        } else {
            event.joinMessage(null);
        }

        if (plugin.getConfig().getBoolean("join.title.enabled")) {
            String titleText = plugin.getConfig().getString("join.title.title");
            String subtitleText = plugin.getConfig().getString("join.title.subtitle");
            int fadeIn = plugin.getConfig().getInt("join.title.fade-in");
            int stay = plugin.getConfig().getInt("join.title.stay");
            int fadeOut = plugin.getConfig().getInt("join.title.fade-out");

            Title.Times times = Title.Times.times(Duration.ofMillis(fadeIn * 50L), Duration.ofMillis(stay * 50L), Duration.ofMillis(fadeOut * 50L));
            Title title = Title.title(formatMessage(titleText, playerName), formatMessage(subtitleText, playerName), times);
            player.showTitle(title);
        }

        if (plugin.getConfig().getBoolean("join.actionbar.enabled")) {
            String message = plugin.getConfig().getString("join.actionbar.message");
            player.sendActionBar(formatMessage(message, playerName));
        }

        if (plugin.getConfig().getBoolean("join.bossbar.enabled")) {
            showBossBar(player, "join");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (plugin.getConfig().getBoolean("quit.sound.enabled")) {
            playSound("quit.sound");
        }

        if (plugin.getConfig().getBoolean("quit.chat.enabled")) {
            String message = plugin.getConfig().getString("quit.chat.message");
            event.quitMessage(formatMessage(message, playerName));
        } else {
            event.quitMessage(null);
        }
    }

    private void playSound(String configPath) {
        try {
            String soundName = plugin.getConfig().getString(configPath + ".name");
            if (soundName == null || soundName.isBlank()) return;

            float volume = (float) plugin.getConfig().getDouble(configPath + ".volume", 1.0);
            float pitch = (float) plugin.getConfig().getDouble(configPath + ".pitch", 1.0);

            Sound sound = Sound.sound(org.bukkit.Sound.valueOf(soundName.toUpperCase()), Sound.Source.MASTER, volume, pitch);
            Bukkit.getServer().playSound(sound);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Invalid sound name in config.yml at path: " + configPath + ".name");
        }
    }

    private void showBossBar(Player player, String type) {
        String message = plugin.getConfig().getString(type + ".bossbar.message");
        String colorStr = plugin.getConfig().getString(type + ".bossbar.color", "WHITE");
        String overlayStr = plugin.getConfig().getString(type + ".bossbar.overlay", "PROGRESS");
        int duration = plugin.getConfig().getInt(type + ".bossbar.duration");

        BossBar.Color color = BossBar.Color.valueOf(colorStr.toUpperCase());
        BossBar.Overlay overlay = BossBar.Overlay.valueOf(overlayStr.toUpperCase());
        BossBar bossBar = BossBar.bossBar(formatMessage(message, player.getName()), 1.0f, color, overlay);

        player.showBossBar(bossBar);

        Bukkit.getScheduler().runTaskLater(plugin, () -> player.hideBossBar(bossBar), duration * 20L);
    }
}
