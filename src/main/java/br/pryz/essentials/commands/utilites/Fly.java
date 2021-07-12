package br.pryz.essentials.commands.utilites;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if (!(s instanceof Player)) {
            if (args.length != 1) {
                s.sendMessage(color("&cUse:&f/voar <Player>. Para alternar o modo de voo!"));
                return true;
            }
            try {
                Player t = Bukkit.getPlayerExact(args[0]);
                if (t.getAllowFlight()) {
                    t.setAllowFlight(false);
                    t.setFlying(false);
                    t.sendMessage(color("&cSeu modo de vôo foi desabilitado!"));
                    s.sendMessage(color("&aVoce &c&ldesabilitou &ao modo de voo do jogador &f&n" + t.getName()));
                } else {
                    t.setAllowFlight(true);
                    t.sendMessage(color("&aSeu modo de voô foi habilitado!"));
                    s.sendMessage(color("&aVoce &lhabilitou &ao modo de voo do jogador &f&n" + t.getName()));
                }
            } catch (Exception ex) {
                s.sendMessage(color("&cNao foi possivel encontrar este jogador!"));
                return true;
            }
            return true;
        }
        Player p = (Player) s;
        if (!p.hasPermission("pry.essentials.command.fly")) {
            p.sendMessage(color("&cVocê não tem permissão para utilizar esse comando&f."));
            return true;
        }
        if (args.length >= 2) {
            p.sendMessage(color("&cUse:&f/voar, ou, /voar <Player>. Para alternar o modo de vôo!"));
            return true;
        }
        if (args.length == 0) {
            if (p.getAllowFlight()) {
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage(color("&cVocê desabilitou seu modo de vôo!"));
            } else {
                p.setAllowFlight(true);
                p.sendMessage(color("&aVocê habilitou seu modo de vôo!"));
            }
            return true;
        } else if (args.length == 1) {
            try {
                Player t = Bukkit.getPlayerExact(args[0]);
                if (t.getAllowFlight()) {
                    t.setAllowFlight(false);
                    t.setFlying(false);
                    t.sendMessage(color("&cSeu modo de vôo foi desabilitado!"));
                    p.sendMessage(color("&aVocê &c&ldesabilitou &ao modo de vôo do jogador &f&n" + t.getName()));
                } else {
                    t.setAllowFlight(true);
                    t.sendMessage(color("&aSeu modo de voô foi habilitado!"));
                    p.sendMessage(color("&aVocê &lhabilitou &ao modo de vôo do jogador &f&n" + t.getName()));
                }
            } catch (Exception ex) {
                p.sendMessage(color("&cNão foi possivél encontrar este jogador!"));
                return true;
            }
        }
        s.sendMessage("Algo deu errado!, Contate o desenvoledor com o codigo: ERRCMD-F0");
        return true;
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
