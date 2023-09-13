package core

import scala.concurrent.Future

trait SftpService[CLIENT] {
  def readFile(client: CLIENT, fileName: String): Future[String]
  def connection(): Future[CLIENT]

}
