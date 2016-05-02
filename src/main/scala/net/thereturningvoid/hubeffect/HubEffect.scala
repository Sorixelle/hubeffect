package net.thereturningvoid.hubeffect

import java.util.logging.Logger

import org.bukkit.plugin.java.JavaPlugin
import Ref._
import net.milkbowl.vault.permission.Permission
import net.thereturningvoid.hubeffect.commands.GiveHubEffectItemCommand
import net.thereturningvoid.hubeffect.listeners.{ItemUseListener, PlayerChangeWorldListener, PlayerDoubleJumpListener, PlayerJoinListener}

class HubEffect extends JavaPlugin {

  val logger: Logger = getLogger
  var perms: Option[Permission] = None

  override def onEnable(): Unit = {
    getConfig.options().copyDefaults(true)
    saveConfig()
    if (!setupPermissions) {
      logger.info("Vault not found! Disabling...")
      this.setEnabled(false)
      return
    }
    registerCommands()
    registerListeners()
    logger.info(s"Enabled HubEffect v$PLUGIN_VERSION")
  }

  override def onDisable(): Unit = {
    logger.info(s"Disabled HubEffect v$PLUGIN_VERSION")
  }

  private def registerCommands(): Unit = {
    getCommand("hubeffectgive").setExecutor(new GiveHubEffectItemCommand(this))
  }

  private def registerListeners(): Unit = {
    getServer.getPluginManager.registerEvents(new ItemUseListener(this), this)
    getServer.getPluginManager.registerEvents(new PlayerDoubleJumpListener(this), this)
    getServer.getPluginManager.registerEvents(new PlayerJoinListener(this), this)
    getServer.getPluginManager.registerEvents(new PlayerChangeWorldListener(this), this)
  }

  private def setupPermissions: Boolean = {
    perms = Option(getServer.getServicesManager.getRegistration(classOf[Permission]).getProvider)
    perms.isDefined
  }

}
