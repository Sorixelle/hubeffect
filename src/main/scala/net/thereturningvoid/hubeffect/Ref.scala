package net.thereturningvoid.hubeffect

import net.md_5.bungee.api.ChatColor

object Ref {

  final val PLUGIN_VERSION: String = "1.0.0"

  final val NOT_PLAYER_COMMAND_MESAGE: String = "Only players can use this command."

  final val CHAT_PREFIX: String =
    s"${ChatColor.DARK_RED}${ChatColor.BOLD}[" +
    s"${ChatColor.GREEN}${ChatColor.BOLD}Hub${ChatColor.DARK_GREEN}${ChatColor.BOLD}Effect" +
    s"${ChatColor.DARK_RED}${ChatColor.BOLD}] ${ChatColor.RESET}"

}
