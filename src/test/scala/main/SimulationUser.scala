package main

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import variables.variable
import scenarios._
import scala.concurrent.duration._

/**
 * {{{
 * Usage:
 * mvn gatling:test -Dgatling.simulationClass=main.SimulationUser -DScenarioName=all -DUserNo=5 -DProxy
 * }}}
 */
class SimulationUser extends Simulation {

  var scenario_str = Option(System.getProperty("ScenarioName")).getOrElse("scn01")
  val user_no_str = Option(System.getProperty("UserNo")).getOrElse("1")

  val scn_all = "scn01,scn02,scn03"

  val protocol = Option(System.getProperty("Proxy")).isDefined match {
    case true => variable.httpProtocol_test
    case false => variable.httpProtocol
  }

  scenario_str = scenario_str.equalsIgnoreCase("all") match {
    case true => scn_all
    case false => scenario_str
  }

  // The following is to setup same injection ways for all scenario
  //  val scenario_list = scenario_str.split(",")
  //    .map(s => scenario_factory(s)
  //      .inject(atOnceUsers(user_no_str.toInt)))

  // TODO after decided scenarios, needed to modify
  // The following is to setup different inject ways for each scenario
  val scenario_list = scenario_str.split(",").collect {
    case "scn01" => scn01.scn1.inject(atOnceUsers(user_no_str.toInt)).protocols(protocol)
    case "scn02" => scn01.scn1.inject(atOnceUsers(user_no_str.toInt)).protocols(protocol)
  }

  def scenario_factory(scenario_name: String): ScenarioBuilder = {
    scenario_name match {
      case "sc01" => scn01.scn1
      case "sc02" => scn01.scn1
    }
  }

  //  setUp(scenario_list: _*).maxDuration(1 minutes).protocols(protocol)
  setUp(scenario_list: _*).maxDuration(1 minutes)
}
