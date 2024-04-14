package core

import scala.concurrent.Future
import scala.util.Try

trait SftpService[CLIENT] {
  def readFile(client: CLIENT, fileName: String): Future[String]
  def connection(): Future[Try[CLIENT]]

}
