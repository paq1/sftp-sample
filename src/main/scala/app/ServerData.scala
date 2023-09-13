package app

import com.typesafe.config.Config

trait ServerData {

  def conf: Config

  val host = s"${conf.getString("sftp.adresse")}:${conf.getString("sftp.port")}"
  val user = s"${conf.getString("sftp.user")}"
  val mdp = s"${conf.getString("sftp.password")}"
  val sshKey = s"00:11:22:33:44:55:66:77:88:99:aa:bb:cc:dd:ee:ff"
  val serverPath = s"home/test/"
}
