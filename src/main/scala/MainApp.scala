import app.DepotFichierServiceSFTP

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.control.NonFatal

object MainApp extends App {
  val service = new DepotFichierServiceSFTP()

  service
    .connection()
    .map { client =>
      println("connection reussie")
      client
    }
    .recoverWith {
      case NonFatal(th) =>
        println("connection echouee")
        Future.failed(th)
    }
    .flatMap(client => service.readFile(client, "test.txt"))
    .map { res: String =>
      println(s"message = $res")
    }
    .recoverWith {
      case NonFatal(th) =>
        println("failure lecture message")
        Future.failed(th)
    }
}
