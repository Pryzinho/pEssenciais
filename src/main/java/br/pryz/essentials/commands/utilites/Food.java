package br.pryz.essentials.commands.utilites;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Food implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if (!(s instanceof Player)) {
            if (args.length != 1) {
                s.sendMessage(color("&cUse:&f/comer <Player>. Regenera sua fome!"));
                return true;
            }
            try {
                Player t = Bukkit.getPlayerExact(args[0]);
                t.setHealth(20);
                t.sendMessage(color("&aUma aura de origem misteriosa entrou em seu corpo e recuperou a sua saciedade."));
                s.sendMessage(color("&aVoce regenerou a saciedade do jogador &f&n" + t.getName()));
            } catch (Exception ex) {
                s.sendMessage(color("&cNao foi possivel encontrar este jogador!"));
                return true;
            }
            return true;
        }
        Player p = (Player) s;
        if (!p.hasPermission("pry.essentials.command.food")) {
            p.sendMessage(color("&cVocê não tem permissão para utilizar esse comando&f."));
            return true;
        }
        if (args.length >= 2) {
            p.sendMessage(color("&cUse:&f/comer, ou, /comer <Player>. Regenera a vida!"));
            return true;
        }
        if (args.length == 0) {
            p.setHealth(20);
            p.sendMessage(color("&aVocê regenerou sua vida!"));
            return true;
        } else if (args.length == 1) {
            try {
                Player t = Bukkit.getPlayerExact(args[0]);
                t.setHealth(20);
                t.sendMessage(color("&aUma aura de origem misteriosa entrou em seu corpo e recuperou a sua saciedade."));
                p.sendMessage(color("&aVoce regenerou a saciedade do jogador &f&n" + t.getName()));
            } catch (Exception ex) {
                p.sendMessage(color("&cNão foi possivél encontrar este jogador!"));
                return true;
            }
        }
        s.sendMessage("Algo deu errado!, Contate o desenvoledor com o codigo: ERRCMD-F1");
        return true;
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
