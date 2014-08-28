package com.github.dnvriend

import akka.actor._
import akka.event.Logging
import akka.osgi.ActorSystemActivator
import com.typesafe.config.{Config, ConfigFactory}
import org.osgi.framework.BundleContext

object Settings {
  val actorSystemName = "AkkaFuseCluster"
  val configName = "application.conf"
}

class Activator extends ActorSystemActivator {

  def configure(bundleContext: BundleContext, system: ActorSystem): Unit = {
    val log = Logging(system, this.getClass)
  }

  override def getActorSystemConfiguration(context: BundleContext): Config = ConfigFactory.load(Settings.configName)

  override def getActorSystemName(context: BundleContext): String = Settings.actorSystemName
}
