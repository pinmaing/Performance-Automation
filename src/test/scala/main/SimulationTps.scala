package main

import io.gatling.core.Predef._
import scenarios._
import variables.variable

import scala.concurrent.duration._

/**
 * Usage:
 * {{{
 * mvn gatling:test -Dgatling.simulationClass=main.SimulationTps -DScenarioName=all
 * mvn gatling:test -Dgatling.simulationClass=main.SimulationTps -DScenarioName=scn01,scn02 -DProxy
 * mvn gatling:test -Dgatling.simulationClass=main.SimulationTps -DScenarioName=scn01,scn02 -DUserNo=5 -DRps=20 -DRpsTime=20 -DHoldTime=3 -DDuration=20 -DProxy
 *
 * }}}
 */
class SimulationTps extends Simulation {

  var scenario_str = Option(System.getProperty("ScenarioName")).getOrElse("scn01")
  val user_no_str = Option(System.getProperty("UserNo")).getOrElse("1")
  val rps_str = Option(System.getProperty("Rps")).getOrElse("1")
  val rps_time_str = Option(System.getProperty("RpsTime")).getOrElse("1")
  val hold_time_str = Option(System.getProperty("HoldTime")).getOrElse("30")
  val usr_duration_str = Option(System.getProperty("Duration")).getOrElse("20")

  val scn_all = "scn01,scn02"

  val protocol = Option(System.getProperty("Proxy")).isDefined match {
    case true => variable.httpProtocol_test
    case false => variable.httpProtocol
  }

  scenario_str = scenario_str.equalsIgnoreCase("all") match {
    case true => scn_all
    case false => scenario_str
  }

  val cnstUserDuration = rps_time_str.toInt + hold_time_str.toInt

  val scenario_list = scenario_str.split(",").collect {
    case "scn01" => scn01.scn1.inject(constantConcurrentUsers(user_no_str.toInt) during (cnstUserDuration second)).protocols(protocol) //atOnceUsers(min_user_no_str.toInt)
    case "scn02" => scn01.scn1.inject(constantConcurrentUsers(user_no_str.toInt) during (cnstUserDuration second)).protocols(protocol) //atOnceUsers(min_user_no_str.toInt)
    }

  setUp(
    scenario_list: _*
  )
    .throttle(
      reachRps(rps_str.toInt) in (rps_time_str.toInt second),
      holdFor(hold_time_str.toInt second)
    )
}
