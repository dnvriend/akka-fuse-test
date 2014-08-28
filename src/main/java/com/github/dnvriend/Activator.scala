package com.github.dnvriend

import akka.actor.ActorSystem
import akka.event.Logging
import akka.osgi.ActorSystemActivator
import org.apache.commons.dbcp.{PoolingDriver, DriverManagerConnectionFactory}
import org.osgi.framework.BundleContext
import scalikejdbc._

class Activator extends ActorSystemActivator {

  def configure(bundleContext: BundleContext, system: ActorSystem): Unit = {
    val log = Logging(system, this.getClass)
    log.info("Hello World!")

    implicit val session = AutoSession
    val driverClassName = "org.postgresql.Driver"
    val url = "jdbc:postgresql://192.168.99.99:5432/propositionengine"

    Class.forName(driverClassName)
    ConnectionPool.singleton(url, "propositionengine", "propositionengine")

    val entities: List[Map[String, Any]] = sql"select * from contract".map(_.toMap).list.apply()
    log.info("{}", entities)
  }
}
