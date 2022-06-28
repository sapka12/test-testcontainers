package testtestcontainers

class EsContainerSpec extends ContainerSpec {
  override def dockerContainer(): String = "docker.elastic.co/elasticsearch/elasticsearch:7.10.1"

  "EsContainerSpec" should {
    "start ES and expose 9200 port" in runTest()
  }
}
