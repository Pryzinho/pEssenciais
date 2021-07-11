package br.pryz.essentials.commands;

import br.pryz.essentials.main.EssentialsMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vanish implements CommandExecutor {
    public static List<Player> invanish = new ArrayList<Player>();
    private HashMap<Player, GameMode> gamemodes = new HashMap<Player, GameMode>();

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage(color("&cSomente jogadores podem utilizar esse comando!"));
            return true;
        }
        Player p = (Player) s;
        if (!p.hasPermission("pry.essentials.command.vanish")) {
            p.sendMessage(color("&cVocê não tem permissão para utilizar esse comando&f."));
            return true;
        }
        if (!invanish.contains(p)) {
            invanish.add(p);
            gamemodes.put(p, p.getGameMode());
            p.setGameMode(GameMode.CREATIVE);
            Bukkit.getOnlinePlayers().stream()
                    .filter(p1 -> !p1.hasPermission("pry.essentials.command.vanish"))
                    .forEach(p2 -> p2.hidePlayer(getPlugin(), p));
            p.sendMessage(color("&aVocê entrou no modo invsivel, somente membros da equipe podem lhe ver!"));
        } else {
            invanish.remove(p);
            p.setGameMode(gamemodes.get(p));
            gamemodes.remove(p);
            p.setHealth(20);
            p.setFoodLevel(20);
            Bukkit.getOnlinePlayers().stream()
                    .filter(p1 -> !p1.hasPermission("pry.essentials.command.vanish"))
                    .forEach(p2 -> p2.showPlayer(getPlugin(), p));
            p.sendMessage(color("&cVocê saiu do modo invsivel, agora, todos podem lhe ver!"));
        }
        return true;
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private JavaPlugin getPlugin() {
        return EssentialsMain.getInstance();
    }
}
