package testtestcontainers

import com.dimafeng.testcontainers.{ForAllTestContainer, GenericContainer}
import io.circe.parser._
import org.scalatest.matchers.should.Matchers
import org.scalatest.{EitherValues, wordspec}
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy

import java.net.URL
import java.time.Duration
import scala.io.Source


trait ContainerSpec extends wordspec.AnyWordSpec with ForAllTestContainer with EitherValues with Matchers {

  override val container = {
    GenericContainer(
      dockerContainer(),
      exposedPorts = Seq(9200),
      waitStrategy = new HttpWaitStrategy()
        .forPort(9200)
        .forStatusCodeMatching(response => response == 200 || response == 401)
        .withStartupTimeout(Duration.ofSeconds(10)),
      env = Map("discovery.type" -> "single-node")
    )
  }

  def dockerContainer(): String

  def runTest() = {
    val result = Source
      .fromInputStream(
        new URL(s"http://${container.containerIpAddress}:${container.mappedPort(9200)}/_cluster/health")
          .openConnection()
          .getInputStream
      )
      .mkString

    val status = parse(result).value.hcursor.downField("status").as[String].value
    status shouldBe "green"
  }

}
