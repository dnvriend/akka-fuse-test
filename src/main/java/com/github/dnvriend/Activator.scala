package com.github.dnvriend

import akka.actor.ActorSystem
import akka.event.Logging
import akka.osgi.ActorSystemActivator
import com.typesafe.config.{ConfigFactory, Config}
import org.osgi.framework.BundleContext

class Activator extends ActorSystemActivator {
  def configure(bundleContext: BundleContext, system: ActorSystem): Unit = {
    val log = Logging(system, this.getClass)
    log.info("Hello World!")
  }
}