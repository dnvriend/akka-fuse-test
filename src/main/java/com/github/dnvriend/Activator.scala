package com.github.dnvriend

import akka.actor._
import akka.event.Logging
import akka.osgi.ActorSystemActivator
import akka.persistence.{Persistent, Processor}
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}
import org.osgi.framework.BundleContext

import scala.concurrent.duration._

class DoorsFsm extends Processor with ActorLogging with FSM[String, Int] {
  log.info("Created Door")
  startWith("closed", 0)

  when("closed") {
    case Event(Persistent("open", _), counter) =>
      log.info("OPEN => [{}]: Recover: [{}], state: {}", stateName, recoveryRunning, counter)
      goto("open") using (counter + 1)

    case Event("state", counter) =>
      log.info("STATE => [{}]: Recover: [{}], state: {}", stateName, recoveryRunning, counter)
      stay() replying ("closed", counter)
  }

  when("open") {
    case Event(Persistent("close", _), counter) =>
      log.info("CLOSE => [{}]: Recover: [{}], state: {}", stateName, recoveryRunning, counter)
      goto("closed") using (counter + 1)

    case Event("state", counter) =>
      log.info("STATE => [{}]: Recover: [{}], state: {}", stateName, recoveryRunning, counter)
      stay() replying ("open", counter)

  }
  initialize()
}

class Launch extends Actor with ActorLogging {
  implicit val timeout = Timeout(1 second)
  log.info("Creating Launch")
  override def receive: Receive = {
    case "GO" =>
      log.info("GO received")
      val actor = context.system.actorOf(Props(new DoorsFsm), "door")
      actor ! "state"
      println("Sending state and sleeping")
      Thread.sleep(500)
      actor ! Persistent("open")
      Thread.sleep(500)
      actor ! "state"
      Thread.sleep(500)
      actor ! PoisonPill
      Thread.sleep(500)
      actor ! "state"
      Thread.sleep(500)
      actor ! Persistent("close")
      Thread.sleep(500)
      actor ! PoisonPill
      Thread.sleep(500)
      actor ! Persistent("open")
      Thread.sleep(500)
      actor ! "state"
  }
}

class Activator extends ActorSystemActivator {

  def configure(bundleContext: BundleContext, system: ActorSystem): Unit = {
    val log = Logging(system, this.getClass)
    system.actorOf(Props(new Launch)) ! "GO"
  }

  override def getActorSystemConfiguration(context: BundleContext): Config = {
    ConfigFactory.load("application.conf")
  }
}
