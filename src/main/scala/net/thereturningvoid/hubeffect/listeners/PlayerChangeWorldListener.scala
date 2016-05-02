package net.thereturningvoid.hubeffect.listeners

import net.thereturningvoid.hubeffect.{HubEffect, HubEffectItem}
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.potion.PotionEffect

import collection.JavaConversions._

class PlayerChangeWorldListener(plugin: HubEffect) extends Listener {

  @EventHandler
  def onPlayerChangeWorld(e: PlayerChangedWorldEvent): Unit = {
    val items: List[HubEffectItem] = HubEffectItem.getItemsFromConfigValues(plugin.getConfig)
    e.getPlayer.getActivePotionEffects foreach { ef =>
      e.getPlayer.removePotionEffect(ef.getType)
    }
    items foreach { i =>
      if (e.getPlayer.getWorld.getName == i.world) {
        i.effects foreach { ef =>
          e.getPlayer.addPotionEffect(new PotionEffect(ef._1, 1000000, ef._2, true, false), true)
        }
      }
    }
  }

}
