package br.pryz.essentials.commands.moderation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import br.pryz.essentials.main.EssentialsMain;
import br.pryz.essentials.utils.Messages;
import br.pryz.essentials.utils.MessageType;

public class VanishFastSwitch implements CommandExecutor {

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
		if (args.length != 0) {
			p.sendMessage(Messages.getString(MessageType.WRONG_USAGE));
			return false;
		}
		p.chat("/vanish");
		new BukkitRunnable() {

			@Override
			public void run() {
				p.chat("/vanish");
			}
		}.runTaskLater(EssentialsMain.getInstance(), 20);
		return true;
	}

}
