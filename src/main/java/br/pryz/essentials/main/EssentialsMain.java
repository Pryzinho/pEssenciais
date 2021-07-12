package br.pryz.essentials.main;

import br.pryz.essentials.commands.utilites.Fly;
import br.pryz.essentials.commands.moderation.Vanish;
import br.pryz.essentials.commands.moderation.VanishFastSwitch;
import br.pryz.essentials.commands.moderation.subcommands.Build;
import br.pryz.essentials.commands.moderation.subcommands.Speak;
import br.pryz.essentials.commands.utilites.Food;
import br.pryz.essentials.commands.utilites.Glow;
import br.pryz.essentials.commands.utilites.Heal;
import br.pryz.essentials.events.PlayerEvent;
import br.pryz.essentials.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialsMain extends JavaPlugin {
    private CommandSender console = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        //Init Messages.yml...
        Messages.getConfig().saveConfig();

        //Init Events
        getServer().getPluginManager().registerEvents(new PlayerEvent(), this);
        //Moderation commands
        getCommand("furtividade").setExecutor(new Vanish()); //Vanish
        getCommand("construir").setExecutor(new Build()); //Build
        getCommand("falar").setExecutor(new Speak()); //Speak
        getCommand("trocarapida").setExecutor(new VanishFastSwitch()); //FastSwitch

        //Utilities Command
        getCommand("voar").setExecutor(new Fly()); //Fly
        getCommand("brilhar").setExecutor(new Glow()); //Glow
        getCommand("regenerar").setExecutor(new Heal()); //Heal
        getCommand("comer").setExecutor(new Food()); //Food

        //Complete init
        console.sendMessage(color("&f[&lp&4Essenciais&f] &aPlugin habilitado&f!"));
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
        console.sendMessage(color("&f[&lp&4Essenciais&f] &cPlugin desabilitado&f!"));
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static JavaPlugin getInstance() {
        return getPlugin(EssentialsMain.class);
    }
}
