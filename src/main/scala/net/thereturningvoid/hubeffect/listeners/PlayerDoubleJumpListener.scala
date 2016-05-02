package net.thereturningvoid.hubeffect.listeners

import net.thereturningvoid.hubeffect.HubEffect
import org.bukkit.{GameMode, Material}
import org.bukkit.entity.Player
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.event.player.{PlayerMoveEvent, PlayerToggleFlightEvent}

class PlayerDoubleJumpListener(plugin: HubEffect) extends Listener {

  @EventHandler
  def onPlayerToggleFlight(e: PlayerToggleFlightEvent): Unit = {
    val p: Player = e.getPlayer
    if (p.getGameMode != GameMode.CREATIVE && p.hasMetadata("hubeffect_doublejump")) {
      e.setCancelled(true)
      p.setAllowFlight(false)
      p.setFlying(false)
      p.setVelocity(p.getLocation.getDirection.multiply(1.25).setY(1))
    }
  }

  @EventHandler
  def onPlayerMove(e: PlayerMoveEvent): Unit = {
    val p: Player = e.getPlayer
    if (p.getGameMode != GameMode.CREATIVE && p.getLocation.subtract(0, 1, 0).getBlock.getType != Material.AIR && !p.isFlying && p.hasMetadata("hubeffect_doublejump")) {
      p.setAllowFlight(true)
    }
  }

}
