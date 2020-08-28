package variables

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object variable {

  val baseURLName = "http://www.twitter.com/"

  val userAgentHeaderName = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36"
   val acceptEncodingHeaderName = "gzip, deflate"
  val acceptLanguageHeaderName = "ja,en-US;q=0.9,en;q=0.8"
  val rumpUpTime = "180"
  val testTimer = "3000"

  val httpProtocol_test = http
    .baseUrl(baseURLName)
    //.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .inferHtmlResources(BlackList(""".*\.js(\?\d*)?""", """.*\.*css(\?[a-zA-Z=+$&:,0-9]*)?""", """.*\.gif(\?\d*)?""", """.*\.jpeg(\?\d*)?""", """.*\.jpg(\?\d*)?""", """.*\.svg(\?\d*)?""", """.*\.ico(\?\d*)?""", """.*\.woff(\?\d*)?""", """.*\.(t|o)tf(\?\d*)?""", """.*\.png(\?\d*)?"""), WhiteList())
    .proxy(
      Proxy("127.0.0.1", 8888)
        .httpsPort(8888)
    )
    .acceptEncodingHeader(acceptEncodingHeaderName)
    .acceptLanguageHeader(acceptLanguageHeaderName)
    .userAgentHeader(userAgentHeaderName)
    .disableCaching

  val httpProtocol = http
    .baseUrl(baseURLName)
    .inferHtmlResources(BlackList(""".*\.js(\?\d*)?""", """.*\.*css(\?[a-zA-Z=+$&:,0-9]*)?""", """.*\.gif(\?\d*)?""", """.*\.jpeg(\?\d*)?""", """.*\.jpg(\?\d*)?""", """.*\.svg(\?\d*)?""", """.*\.ico(\?\d*)?""", """.*\.woff(\?\d*)?""", """.*\.(t|o)tf(\?\d*)?""", """.*\.png(\?\d*)?"""), WhiteList())
    .acceptEncodingHeader(acceptEncodingHeaderName)
    .acceptLanguageHeader(acceptLanguageHeaderName)
    .userAgentHeader(userAgentHeaderName)
}
