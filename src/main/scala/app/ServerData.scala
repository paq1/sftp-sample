package app

import com.typesafe.config.Config

trait ServerData {

  def conf: Config

  val host: String = conf.getString("sftp.adresse")
  val port: Int = conf.getString("sftp.port").toInt
  val user = s"${conf.getString("sftp.user")}"
  val mdp = s"${conf.getString("sftp.password")}"
  val sshKey = s"a1:87:cd:ba:7e:99:1e:df:fd:e3:09:69:17:3f:fa:98"
  val serverPath = s"home/test/"
}
