package br.pryz.essentials.commands.utilites;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Glow implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if (!(s instanceof Player)) {
            if (args.length != 1) {
                s.sendMessage(color("&cUse:&f/brilhar <Player>. Para alternar o modo de brilho!"));
                return true;
            }
            try {
                Player t = Bukkit.getPlayerExact(args[0]);
                if (t.isGlowing()) {
                    t.setGlowing(false);
                    t.sendMessage(color("&cVocê parou de brilhar, o fragmento se esvaziou."));
                    s.sendMessage(color("&aVoce &c&ldesabilitou &ao brilho do jogador &f&n" + t.getName()));
                } else {
                    t.setGlowing(true);
                    t.sendMessage(color("&aVocê foi atingido por um fragmento estelar, seu corpo começou a brilhar! "));
                    s.sendMessage(color("&aVoce &lhabilitou &ao brilho do jogador &f&n" + t.getName()));
                }
            } catch (Exception ex) {
                s.sendMessage(color("&cNao foi possivel encontrar este jogador!"));
                return true;
            }
            return true;
        }
        Player p = (Player) s;
        if (!p.hasPermission("pry.essentials.command.glow")) {
            p.sendMessage(color("&cVocê não tem permissão para utilizar esse comando&f."));
            return true;
        }
        if (args.length >= 2) {
            p.sendMessage(color("&cUse:&f/brilhar, ou, /brilhar <Player>. Para alternar o modo de brilho!"));
            return true;
        }
        if (args.length == 0) {
            if (p.isGlowing()) {
                p.setGlowing(false);
                p.sendMessage(color("&cVocê parou de brilhar, o fragmento se esvaziou."));
            } else {
                p.setGlowing(true);
                p.sendMessage(color("&aVocê foi atingido por um fragmento estelar, seu corpo começou a brilhar!"));
            }
            return true;
        } else if (args.length == 1) {
            try {
                Player t = Bukkit.getPlayerExact(args[0]);
                if (t.isGlowing()) {
                    t.setGlowing(false);
                    t.sendMessage(color("&cVocê parou de brilhar, o fragmento se esvaziou."));
                    p.sendMessage(color("&aVoce &c&ldesabilitou &ao brilho do jogador &f&n" + t.getName()));
                } else {
                    t.setGlowing(true);
                    t.sendMessage(color("&aVocê foi atingido por um fragmento estelar, seu corpo começou a brilhar!"));
                    p.sendMessage(color("&aVoce &lhabilitou &ao brilho do jogador &f&n" + t.getName()));
                }
            } catch (Exception ex) {
                p.sendMessage(color("&cNão foi possivél encontrar este jogador!"));
                return true;
            }
        }
        s.sendMessage("Algo deu errado!, Contate o desenvoledor com o codigo: ERRCMD-G0");
        return true;
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
