package app

import akka.util.ByteString
import com.typesafe.config.Config
import core.SftpService
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.sftp.SFTPClient

import java.nio.ByteBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class DepotFichierServiceSFTP(
    override val conf: Config
)(implicit ec: ExecutionContext)
    extends ServerData
    with SftpService[SFTPClient] {

  def connection(): Future[Try[SFTPClient]] = {
    Future.successful {
      Try {
        println(host)
        val sshClient = new SSHClient()
        sshClient.addHostKeyVerifier(sshKey)
        sshClient.connect(host, 22)
        sshClient.authPassword(user, mdp)
        sshClient.newSFTPClient()
      }
    }
  }

  def readFile(client: SFTPClient, fileName: String): Future[String] =
    Future {
      val serverPathFile = s"$serverPath$fileName"
      val remoteFile = client
        .open(serverPathFile)
      val taille = remoteFile.length()
      val buffer = ByteBuffer.allocate(taille.toInt)
      buffer.array()
      if (remoteFile.read(0, buffer.array(), 0, taille.toInt) == -1) {
        Future.failed(throw new Exception("lecture failed"))
      } else {
        Future(ByteString(buffer.array()).utf8String)
      }
    }.flatten
}
