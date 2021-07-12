package br.pryz.essentials.events;

import br.pryz.essentials.commands.moderation.Vanish;
import br.pryz.essentials.main.EssentialsMain;
import br.pryz.essentials.utils.MessageType;
import br.pryz.essentials.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.HashMap;

public class PlayerEvent implements Listener {
    private static final JavaPlugin pl = EssentialsMain.getInstance();
    public static HashMap<Player, Boolean> chat = new HashMap<>();
    public static HashMap<Player, Boolean> build = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPermission("pry.essentials.command.vanish")) {
            Vanish.invanish.forEach(p -> e.getPlayer().hidePlayer(pl, p));
        }
    }

    @EventHandler
    public void onChatInVanish(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Vanish.invanish.contains(p)) {
            if (!chat.get(p)) {
                p.sendMessage(Messages.getString(MessageType.CANT_SPEAK));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlaceBlockInVanish(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (Vanish.invanish.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(Messages.getString(MessageType.CANT_BUILD));
        }
    }

    @EventHandler
    public void onBreakBlockInVanish(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (Vanish.invanish.contains(p)) {
            if (!build.get(p)) {
                e.setCancelled(true);
                p.sendMessage(Messages.getString(MessageType.CANT_BUILD));

            }
        }
    }

    @EventHandler
    public void onPickItemInVanish(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if (Vanish.invanish.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(Messages.getString(MessageType.CANT_PICK));
        }
    }

    @EventHandler
    public void onDropItemInVanish(PlayerDropItemEvent e) {
        if (Vanish.invanish.contains(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Messages.getString(MessageType.CANT_DROP));
        }

    }

    @EventHandler
    public void onMemberInVanishClickCreativeInventory(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player p = (Player) e.getWhoClicked();
        if (Vanish.invanish.contains(p)) {
            if (e.getInventory().getType() != InventoryType.CREATIVE) return;
            p.sendMessage(Messages.getString(MessageType.CANT_GIVE));
            Bukkit.getOnlinePlayers().stream().filter(p2 -> p2.hasPermission("pry.essentials.level.admin"))
                    .forEach(p2 -> p2.sendMessage(color("&cO Staff &f&l") + p.getName() + color(" &ctentou pegar um item no modo de jogo criativo!"))
                    );
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 50, 50);
            e.setResult(org.bukkit.event.Event.Result.DENY);
        }
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
