package net.thereturningvoid.hubeffect.commands

import net.md_5.bungee.api.ChatColor
import net.thereturningvoid.hubeffect.HubEffect
import net.thereturningvoid.hubeffect.Ref._
import net.thereturningvoid.hubeffect.Predefs._
import org.bukkit.Material
import org.bukkit.command.{Command, CommandExecutor, CommandSender}
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

import collection.JavaConversions._

class GiveHubEffectItemCommand(plugin: HubEffect) extends CommandExecutor {

  override def onCommand(sender: CommandSender, cmd: Command, label: String, args: Array[String]): Boolean = {
    sender match {
      case p: Player =>
        if (plugin.perms.get.has(p, "hubeffect.admin")) {
          if (args.length < 1) {
            p.sendMessage(s"${CHAT_PREFIX}Not enough arguments!")
            p.sendMessage(s"${CHAT_PREFIX}Usage: /hegive <itemname>")
            return true
          } else {
            val config: FileConfiguration = plugin.getConfig
            val itemKey: Option[String] = config.getConfigurationSection("items").getValues(true) collectFirst {
              case (k, v) if k == args(0) => s"items.$k"
            }
            if (itemKey.isDefined) {
              val itemStack: ItemStack = new ItemStack(Material.matchMaterial(config.getString(itemKey.get + ".offItem.type"))) tap { i =>
                i.setItemMeta(i.getItemMeta tap { m =>
                  m.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString(itemKey.get + ".offItem.name")))
                })
              }
              p.getInventory.addItem(itemStack)
              p.sendMessage(s"${CHAT_PREFIX}Item added!")
            } else {
              p.sendMessage(s"${CHAT_PREFIX}That item does not exist!")
              p.sendMessage(s"${CHAT_PREFIX}Usage: /hegive <itemname>")
              return true
            }
            return true
          }
        } else {
          p.sendMessage(s"${CHAT_PREFIX}You do not have the required permissions!")
          return true
        }
      case _ =>
        sender.sendMessage(NOT_PLAYER_COMMAND_MESAGE)
        return true
    }
    false
  }

}
