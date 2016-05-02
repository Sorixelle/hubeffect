package net.thereturningvoid.hubeffect

import org.bukkit.{Bukkit, ChatColor, Material, World}
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffectType
import net.thereturningvoid.hubeffect.Predefs._
import org.bukkit.configuration.file.FileConfiguration

import scala.collection.immutable.Map
import collection.JavaConversions._

/**
  * Created by retvoid on 27/04/16.
  */
case class HubEffectItem(name: String, offItem: ItemStack, onItem: ItemStack, effects: List[(PotionEffectType, Int)],
                         invincibility: Boolean, doubleJump: Boolean, enableMsg: String, disableMsg: String,
                         world: String)

object HubEffectItem {
  def getItemsFromConfigValues(config: FileConfiguration): List[HubEffectItem] = {
    val items: Map[String, AnyRef] = config.getConfigurationSection("items").getValues(false).toMap
    items map { case (k, v) =>
      val key: String = s"items.$k"
      val offMaterial: Material = Material.matchMaterial(config.getString(s"$key.offItem.type"))
      val offName: String = ChatColor.translateAlternateColorCodes('&', config.getString(s"$key.offItem.name"))
      val off: ItemStack = new ItemStack(offMaterial) tap {i =>
        i.setItemMeta(i.getItemMeta tap {m =>
          m.setDisplayName(offName)
        })
      }

      val onMaterial: Material = Material.matchMaterial(config.getString(s"$key.onItem.type"))
      val onName: String = ChatColor.translateAlternateColorCodes('&', config.getString(s"$key.onItem.name"))
      val on: ItemStack = new ItemStack(onMaterial) tap {i =>
        i.setItemMeta(i.getItemMeta tap {m =>
          m.setDisplayName(onName)
        })
      }

      val effects: List[(PotionEffectType, Int)] = config.getStringList(s"$key.effects") map {e =>
        val split: Array[String] = e.split('|')
        (PotionEffectType.getByName(split(0).toUpperCase), split(1).toInt)
      } toList

      val itemWorld: Option[String] = Bukkit.getWorlds.collectFirst{ case w if w.getName == config.getString(s"$key.world") => w.getName }

      new HubEffectItem(k, off, on, effects, config.getBoolean(s"$key.invincible", false), config.getBoolean(s"$key.doublejump", false),
        ChatColor.translateAlternateColorCodes('&', config.getString(s"$key.enablemsg")),
        ChatColor.translateAlternateColorCodes('&', config.getString(s"$key.disablemsg")), itemWorld.getOrElse(""))
    } toList
  }
}
