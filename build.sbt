name := "HubEffect"

version := "1.0.0"

scalaVersion := "2.11.8"

resolvers += "Spigot Repo" at "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"
resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "Vault Repo" at "http://nexus.theyeticave.net/content/repositories/pub_releases/"

libraryDependencies += "org.spigotmc" % "spigot-api" % """1.9.2-R0.1-SNAPSHOT""" % Provided
libraryDependencies += "net.milkbowl.vault" % "VaultAPI" % "1.6" % Provided