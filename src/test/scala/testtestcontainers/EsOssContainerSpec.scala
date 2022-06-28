package testtestcontainers

class EsOssContainerSpec extends ContainerSpec {
  override def dockerContainer(): String = "docker.elastic.co/elasticsearch/elasticsearch-oss:7.10.1"

  "EsOssContainerSpec" should {
    "start ES and expose 9200 port" in runTest()
  }
}
