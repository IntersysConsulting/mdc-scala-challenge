package com.intersys.mdc.challenge

import com.typesafe.config.{Config, ConfigFactory}

object Settings {
  private val app: Config = ConfigFactory.load().getConfig("application")
  object Http {
    private val http: Config = app.getConfig("http")
    val host: String = http.getString("host")
    val port: Int    = http.getInt("port")
  }
}
