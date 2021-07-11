package br.pryz.essentials.utils;

import org.bukkit.ChatColor;
import br.pryz.essentials.main.EssentialsMain;

public class Messages {
    private static final PryConfig mensagens = new PryConfig(EssentialsMain.getInstance(), "mensagens.yml");

    public static PryConfig getConfig() {
        return mensagens;
    }
    public static String getString(MessageType messagetype) {
        String path = messagetype.toString();
        if (mensagens.getString(path) != null) {
            return ChatColor.translateAlternateColorCodes('&', mensagens.getString(path));
        } else {
            mensagens.saveConfig();
            return "Messages.yml não encontrada.";
        }
    }

}
