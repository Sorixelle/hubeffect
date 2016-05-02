package net.thereturningvoid.hubeffect.listeners

import net.thereturningvoid.hubeffect.{HubEffect, HubEffectItem}
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.potion.PotionEffect

import collection.JavaConversions._

class ItemUseListener(plugin: HubEffect) extends Listener {

  @EventHandler
  def onItemUse(e: PlayerInteractEvent): Unit = {
    if (e.getAction == Action.RIGHT_CLICK_AIR || e.getAction == Action.RIGHT_CLICK_BLOCK) {
      val p: Player = e.getPlayer
      val items: List[HubEffectItem] = HubEffectItem.getItemsFromConfigValues(plugin.getConfig)
      items foreach { i =>
        val hand: ItemStack = p.getInventory.getItemInMainHand
        if (hand == i.offItem || hand == i.onItem) {
          if (!p.hasMetadata("hubeffect_applyeffects")) {
            p.setMetadata("hubeffect_applyeffects", new FixedMetadataValue(plugin, "value"))
            p.addPotionEffects(i.effects map (e => new PotionEffect(e._1, 1000000, e._2, true, false)))
            p.setInvulnerable(i.invincibility)
            if (i.doubleJump) p.setMetadata("hubeffect_doublejump", new FixedMetadataValue(plugin, "value"))
            p.getInventory.setItemInMainHand(i.onItem)
            p.sendMessage(i.enableMsg)
          } else {
            p.removeMetadata("hubeffect_applyeffects", plugin)
            i.effects foreach (e => p.removePotionEffect(e._1))
            p.setInvulnerable(false)
            p.removeMetadata("hubeffect_doublejump", plugin)
            p.getInventory.setItemInMainHand(i.offItem)
            p.sendMessage(i.disableMsg)
          }
        }
      }
    }
  }

}