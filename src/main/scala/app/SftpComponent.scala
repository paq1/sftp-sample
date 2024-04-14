package app

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

trait SftpComponent { self: MainComponent =>
  val service: DepotFichierServiceSFTP = new DepotFichierServiceSFTP(conf)

  def run(): Future[Unit] = service
    .connection()
    .flatMap {
      case Failure(th) =>
        println(th.toString)
        Future.successful(None)
      case Success(client) =>
        println("connecter")
        service
          .readFile(client, "test.txt")
          .map(Some(_))
    }
    .map(_.map(println))
}
