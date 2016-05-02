package net.thereturningvoid.hubeffect.listeners

import net.thereturningvoid.hubeffect.{HubEffect, HubEffectItem}
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.player.{PlayerJoinEvent, PlayerQuitEvent}
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.inventory.ItemStack

import collection.JavaConversions._
import net.thereturningvoid.hubeffect.Predefs._
import org.bukkit.{ChatColor, Material}

class PlayerJoinListener(plugin: HubEffect) extends Listener {

  val cfg: FileConfiguration = plugin.getConfig

  @EventHandler
  def onPlayerJoin(e: PlayerJoinEvent): Unit =
    HubEffectItem.getItemsFromConfigValues(cfg) foreach { i =>
      if (plugin.perms.get.has(e.getPlayer, s"hubeffect.${i.name}")) {
        if (e.getPlayer.hasMetadata("hubeffect_applyeffects")) {
          e.getPlayer.getInventory.addItem(i.onItem)
        } else {
          e.getPlayer.getInventory.addItem(i.offItem)
        }
      }
    }

  @EventHandler
  def onPlayerLeave(e: PlayerQuitEvent): Unit =
    HubEffectItem.getItemsFromConfigValues(cfg) foreach { i =>
      if (plugin.perms.get.has(e.getPlayer, s"hubeffect.${i.name}")) {
        e.getPlayer.getInventory.all(i.offItem.getType) foreach { item =>
          if (item._2.getItemMeta.getDisplayName == i.offItem.getItemMeta.getDisplayName) {
            e.getPlayer.getInventory.clear(item._1)
          }
        }
        e.getPlayer.getInventory.all(i.onItem.getType) foreach { item =>
          if (item._2.getItemMeta.getDisplayName == i.onItem.getItemMeta.getDisplayName) {
            e.getPlayer.getInventory.clear(item._1)
          }
        }
      }
    }

}
