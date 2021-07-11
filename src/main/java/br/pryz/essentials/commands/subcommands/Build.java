package br.pryz.essentials.commands.subcommands;

import br.pryz.essentials.commands.Vanish;
import br.pryz.essentials.events.PlayerEvent;
import br.pryz.essentials.utils.MessageType;
import br.pryz.essentials.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Build implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage(Messages.getString(MessageType.NOT_A_PLAYER));
            return false;
        }
        Player p = (Player) s;
        if (!p.hasPermission("pry.essentials.command.vanish")) {
            p.sendMessage(Messages.getString(MessageType.MISSING_PERMISSIONS));
            return false;
        }
        if (args.length > 0) {
            p.sendMessage(Messages.getString(MessageType.WRONG_USAGE));
            return false;
        }
        if (!Vanish.invanish.contains(p)) {
            p.sendMessage(Messages.getString(MessageType.NOT_VANISHED));
            return false;
        }
        if (!PlayerEvent.build.get(p)) {
            PlayerEvent.build.replace(p, true);
            p.sendMessage(Messages.getString(MessageType.NOW_CAN_BREAK));
        } else {
            PlayerEvent.build.replace(p, false);
            p.sendMessage(Messages.getString(MessageType.NOW_CANT_BREAK));
        }
        return true;
    }

}
