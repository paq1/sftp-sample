package app

import com.typesafe.config.{Config, ConfigFactory}

trait ConfigurationComponent {
  def conf: Config = ConfigFactory.load()
}
