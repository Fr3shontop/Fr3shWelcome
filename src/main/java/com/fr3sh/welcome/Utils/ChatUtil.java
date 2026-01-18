package com.fr3sh.welcome.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ChatUtil {

    public static Component format(String message) {
        if (message == null || message.isEmpty()) {
            return Component.empty();
        }
        // Standard MiniMessage parser with all features enabled.
        return MiniMessage.miniMessage().deserialize(message);
    }
}
