import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class UserControllerTest extends Simulation{
  val httpConf = http.baseUrl("http://arch.homework:8000")

  val scan = scenario("Test Key")
    .exec(http("Create user")
      .post("/identity/user")
      .header("Content-Type", "application/json")
      .body(StringBody("{\n    \"company\": {\n        \"id\": \"14eaad53-0a81-4e70-907a-80743f0a2fe0\",\n        \"egrpo\": \"{{companyErgpo}}\",\n        \"name\": \"{{companyName}}\"\n    },\n    \"email\": \"{{email}}\",\n    \"firstName\": \"{{firstName}}\",\n    \"lastName\": \"{{lastName}}\",\n    \"mobilePhone\": \"{{mobilePhone}}\",\n    \"signature\": \"NmFlNzU0ZDgtZDU3My0xMWVhLTg3ZDAtMDI0MmFjMTMwMDAz\"\n}")).asJson)
    .exec(http("Login user")
      .post("/identity/user/login")
      .header("Content-Type", "application/json")
      .body(StringBody("{\n    \"signature\": \"NmFlNzU0ZDgtZDU3My0xMWVhLTg3ZDAtMDI0MmFjMTMwMDAz\"\n}")).asJson
      .check(jsonPath("$.jwt").saveAs("jwt")))
//    .exec(http("Create ETTN")
//      .post("/ettn/originator")
//      .header("Content-Type", "application/json")
//      .header("Authorization", "Bearer "+ "${jwt}")
//      .body(StringBody("{\n    \"xml\": \"PERFQ0xBUiB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIj4KICAgIDxTSUdOX0VOVkVMT1BFIFNUQVRFPSJPUklHSU5BVE9SX1NJR05FRCI+CiAgICAgICAgPERFQ0xBUkhFQUQ+CiAgICAgICAgICAgIDxDX0RPQz5UMDE8L0NfRE9DPgogICAgICAgICAgICA8Q19ET0NfU1VCPjAwMTwvQ19ET0NfU1VCPgogICAgICAgICAgICA8Q19ET0NfVkVSPjAyPC9DX0RPQ19WRVI+CiAgICAgICAgPC9ERUNMQVJIRUFEPgoJICAgIDxERUNMQVJCT0RZIFRZUEU9IkNPUkUiIFNUQUtFPSJPUklHSU5BVE9SIj4KICAgICAgICAgICAgPEhGSUxMPjIwMjAtMDQtMjA8L0hGSUxMPgogICAgICAgICAgICA8SE5VTT7QotCcMDAwMDAwMDEwMjc5PC9ITlVNPgogICAgICAgICAgICA8RE9DVU1FTlRfUExBQ0U+0Lwu0JrQuNGX0LI8L0RPQ1VNRU5UX1BMQUNFPgogICAgICAgICAgICA8UjAxRzFTPkRBRjwvUjAxRzFTPgogICAgICAgICAgICA8UjAxRzExUz5YRjEwNTwvUjAxRzExUz4KICAgICAgICAgICAgPFIwMUcyUz7QkNCdNjc1NNCV0JA8L1IwMUcyUz4KICAgICAgICAgICAgPFIwMUczUz7QsNCy0YLQvjwvUjAxRzNTPgogICAgICAgICAgICA8UjAxRzRTPkRBRjwvUjAxRzRTPgogICAgICAgICAgICA8UjAxRzVTPtCQ0J0yMzI10JXQkDwvUjAxRzVTPgogICAgICAgICAgICA8UjAxRzZTPtC/0YDQuNGH0ZbQvzE8L1IwMUc2Uz4KICAgICAgICAgICAgPFIwMUc3Uz5EQUY8L1IwMUc3Uz4KICAgICAgICAgICAgPFIwMUc4Uz7QkNCdMTExMdCV0JA8L1IwMUc4Uz4KICAgICAgICAgICAgPFIwMUc5Uz7Qv9GA0LjRh9GW0L8yPC9SMDFHOVM+CiAgICAgICAgICAgIDxSMDFHMTBTPtCy0LDQvdGC0LDQttC90ZYg0L/QtdGA0LXQstC10LfQtdC90L3RjzwvUjAxRzEwUz4KICAgICAgICAgICAgPFIwMkcxMVM+MTEyMjMzNDQ8L1IwMkcxMVM+CiAgICAgICAgICAgIDxSMDJHMVM+0J/QtdGA0LXQstGW0LfQvdC40LogMTwvUjAyRzFTPgogICAgICAgICAgICA8UjAyRzIxUz4zNDU1NDM2MjwvUjAyRzIxUz4KICAgICAgICAgICAgPFIwMkcyUz7Ql9Cw0LzQvtCy0L3QuNC6INCi0LXRgdGC0L7QstC40Lkg0L/Qu9Cw0YLQvdC40LogMzwvUjAyRzJTPgogICAgICAgICAgICA8UjAyRzNTPtCo0LXQstGH0LXQvdC60L48L1IwMkczUz4KICAgICAgICAgICAgPFIwMkczMVM+0KLQsNGA0LDRgTwvUjAyRzMxUz4KICAgICAgICAgICAgPFIwMkczMlM+0JPRgNC40LPQvtGA0L7QstC40Yc8L1IwMkczMlM+CiAgICAgICAgICAgIDxSMDJHNFM+0JDQkDEyMzQ1NjwvUjAyRzRTPgogICAgICAgICAgICA8SFRJTj4zNDU1NDM2MjwvSFRJTj4KICAgICAgICAgICAgPEhOQU1FPtCi0LXRgdGC0L7QstC40Lkg0L/Qu9Cw0YLQvdC40LogMzwvSE5BTUU+CiAgICAgICAgICAgIDxITE9DPtCj0JrQoNCQ0IfQndCQLCA4ODc0NSwg0KfQldCg0JrQkNCh0KzQmtCQINCe0JHQm9CQ0KHQotCsLCDQltCQ0KjQmknQktCh0KzQmtCY0Jkg0KDQkNCZ0J7QnSDQoC3QnSwg0Jwu0JbQkNCo0JrQhtCSLCDQktCj0JsuINCb0JXQndCG0J3QkCwg0JHQo9CULiAyMiwg0JrQki4gKNCe0KTQhtChKSAzPC9ITE9DPgogICAgICAgICAgICA8UjA0RzFTPjMzNDQ1NTY2PC9SMDRHMVM+CiAgICAgICAgICAgIDxSMDRHMlM+0KLQtdGB0YLQvtCy0LjQuSDQv9C70LDRgtC90LjQuiA0PC9SMDRHMlM+CiAgICAgICAgICAgIDxSMDRHM1M+0KPQmtCg0JDQh9Cd0JAsIDg4NzQ1LCDQnC4g0JrQmNCH0JIsINCS0KPQmy4g0JvQldCd0IbQndCQLCDQkdCj0JQuIDIyLCDQmtCSLiAo0J7QpNCG0KEpIDM8L1IwNEczUz4KICAgICAgICAgICAgPFIwNUcyMT44MDAwMDAwMDAwPC9SMDVHMjE+CiAgICAgICAgICAgIDxSMDVHMlM+0JrQuNGX0LIsINC/0YAuINCi0LDRgNCw0YHQsCDQqNC10LLRh9C10L3QutC+PC9SMDVHMlM+CiAgICAgICAgICAgIDxSMDVHNDE+ODAwMDAwMDAwMDwvUjA1RzQxPgogICAgICAgICAgICA8UjA1RzRTPtCa0LjRl9CyLCDQpdGA0LXRidCw0YLQuNC6PC9SMDVHNFM+CiAgICAgICAgICAgIDxSMDEyRzNTPtC00LLQsNC90LDQtNGG0Y/RgtGMPC9SMDEyRzNTPgogICAgICAgICAgICA8UjAxM0cxPjIwMC4wMDwvUjAxM0cxPgogICAgICAgICAgICA8UjAxM0cyUz7QtNCy0ZbRgdGC0ZY8L1IwMTNHMlM+CiAgICAgICAgICAgIDxSMDEwRzNTPtGB0YLQviDRgtC40YHRj9GHPC9SMDEwRzNTPgogICAgICAgICAgICA8UjAxMUcxPjE2NjY2LjY3PC9SMDExRzE+CiAgICAgICAgICAgIDxSMDE0RzFTPtGB0LXRgNGC0LjRhNGW0LrQsNGCINGP0LrQvtGB0YLRljwvUjAxNEcxUz4KICAgICAgICAgICAgPFNFQUxfTk8+0L/Qu9C+0LzQsdCwIDEyMywg0L/Qu9C+0LzQsdCwIDQ1NjwvU0VBTF9OTz4KICAgICAgICAgICAgPFRFTVBFUkFUVVJFPtCi0LXQvNC/0LXRgNCw0YLRg9GA0L3QuNC5INGA0LXQttC40Lw8L1RFTVBFUkFUVVJFPgogICAgICAgICAgICA8VDFSWFhYWEc4MVMgUk9XTlVNPSIxIj7Qv9C+0LHRg9GC0L7QstCwINGC0LXRhdC90ZbQutCwPC9UMVJYWFhYRzgxUz4KICAgICAgICAgICAgPFQxUlhYWFhHODIgUk9XTlVNPSIxIiB4c2k6bmlsPSJ0cnVlIi8+CiAgICAgICAgICAgIDxUMVJYWFhYRzlTIFJPV05VTT0iMSI+0YjRgjwvVDFSWFhYWEc5Uz4KICAgICAgICAgICAgPFQxUlhYWFhHMTAgUk9XTlVNPSIxIj4xMjwvVDFSWFhYWEcxMD4KICAgICAgICAgICAgPFQxUlhYWFhHMTEgUk9XTlVNPSIxIj4xMDAuMDA8L1QxUlhYWFhHMTE+CiAgICAgICAgICAgIDxUMVJYWFhYRzEyIFJPV05VTT0iMSI+MTQ0MC4wMDwvVDFSWFhYWEcxMj4KICAgICAgICAgICAgPFQxUlhYWFhHMTNTIFJPV05VTT0iMSI+0LrQvtGA0L7QsdC60LA8L1QxUlhYWFhHMTNTPgogICAgICAgICAgICA8VDFSWFhYWEcxNFMgUk9XTlVNPSIxIj7RgdC10YDRgtC40YTRltC60LDRgiDRj9C60L7RgdGC0ZY8L1QxUlhYWFhHMTRTPgogICAgICAgICAgICA8VDFSWFhYWEcxNSBST1dOVU09IjEiPjEuMDA8L1QxUlhYWFhHMTU+CiAgICAgICAgICAgIDxUMVJYWFhYRzgxUyBST1dOVU09IjIiPtC90LXQsdC10LfQv9C10YfQvdC40Lkg0LLQsNC90YLQsNC2PC9UMVJYWFhYRzgxUz4KICAgICAgICAgICAgPFQxUlhYWFhHODIgUk9XTlVNPSIyIj4xLjE8L1QxUlhYWFhHODI+CiAgICAgICAgICAgIDxUMVJYWFhYRzlTIFJPV05VTT0iMiI+0YjRgjwvVDFSWFhYWEc5Uz4KICAgICAgICAgICAgPFQxUlhYWFhHMTAgUk9XTlVNPSIyIj4xMjwvVDFSWFhYWEcxMD4KICAgICAgICAgICAgPFQxUlhYWFhHMTEgUk9XTlVNPSIyIj4yMDAuMDA8L1QxUlhYWFhHMTE+CiAgICAgICAgICAgIDxUMVJYWFhYRzEyIFJPV05VTT0iMiI+MzAwLjAwPC9UMVJYWFhYRzEyPgogICAgICAgICAgICA8VDFSWFhYWEcxM1MgUk9XTlVNPSIyIj7QsdC+0YfQutCwPC9UMVJYWFhYRzEzUz4KICAgICAgICAgICAgPFQxUlhYWFhHMTRTIFJPV05VTT0iMiIgeHNpOm5pbD0idHJ1ZSIgLz4KICAgICAgICAgICAgPFQxUlhYWFhHMTUgUk9XTlVNPSIyIj4xMi4wPC9UMVJYWFhYRzE1PgogICAgICAgICAgICA8UjAwMUcxMD4xMi4wMDwvUjAwMUcxMD4KICAgICAgICAgICAgPFIwMDFHMTI+MTQ0MC4wMDwvUjAwMUcxMj4KICAgICAgICAgICAgPFIwOEcyPjEuMDA8L1IwOEcyPgogICAgICAgICAgICA8REFURV9BUlJJVkFMX0xPQUQ+MjAyMC0wNy0wMTwvREFURV9BUlJJVkFMX0xPQUQ+CiAgICAgICAgICAgIDxSMDhHMzE+OTwvUjA4RzMxPgogICAgICAgICAgICA8UjA4RzMyPjA8L1IwOEczMj4KICAgICAgICAgICAgPERBVEVfREVQQVJUVVJFX0xPQUQ+MjAyMC0wNy0wMTwvREFURV9ERVBBUlRVUkVfTE9BRD4KICAgICAgICAgICAgPFIwOEc0MT4xMDwvUjA4RzQxPgogICAgICAgICAgICA8UjA4RzQyPjA8L1IwOEc0Mj4KICAgICAgICAgICAgPFIwOEc1MT4yPC9SMDhHNTE+CiAgICAgICAgICAgIDxSMDhHNTI+MjwvUjA4RzUyPgogICAgICAgICAgICA8UjAxN0cxMVM+0LrQvtC80ZbRgNC90LjQujwvUjAxN0cxMVM+CiAgICAgICAgICAgIDxSMDE3RzFTPtCi0LrQsNGH0LXQvdC60L48L1IwMTdHMVM+CiAgICAgICAgICAgIDxERUNMQVJCT0RZX09SSUdJTkFUT1JfU0VDVVJJVFk+CiAgICAgICAgICAgICAgICA8RklSU1RfTkFNRT7QkdC+0LPQtNCw0L08L0ZJUlNUX05BTUU+CiAgICAgICAgICAgICAgICA8TEFTVF9OQU1FPtCl0LzQtdC70YzQvdC40YbRjNC60LjQuTwvTEFTVF9OQU1FPgogICAgICAgICAgICAgICAgPEZBVEhFUlNfTkFNRT7QnNC40YXQsNC50LvQvtCy0LjRhzwvRkFUSEVSU19OQU1FPgogICAgICAgICAgICAgICAgPERPQ1VNRU5UX1RZUEU+0LTQvtCz0L7QstGW0YAg0YnQvtC00L4g0L3QsNC00LDQvdC90Y8g0L/QvtGB0LvRg9GFINCyINGB0YTQtdGA0ZYg0L7RhdC+0YDQvtC90Lgg0LLQsNC90YLQsNC20ZbQsjwvRE9DVU1FTlRfVFlQRT4KICAgICAgICAgICAgICAgIDxET0NVTUVOVF9JRD7ihJYyMzQ1LdCk0KQ8L0RPQ1VNRU5UX0lEPgogICAgICAgICAgICAgICAgPERPQ1VNRU5UX0RBVEU+MjAyMC0wNC0wMTwvRE9DVU1FTlRfREFURT4KICAgICAgICAgICAgPC9ERUNMQVJCT0RZX09SSUdJTkFUT1JfU0VDVVJJVFk+CiAgICAgICAgPC9ERUNMQVJCT0RZPgogICAgICAgIDxERUNMQVJFWFQ+CiAgICAgICAgICAgIDxFWFRFTlNJT04gVFlQRT0iRU1BSUwiIFNUQUtFPSJSRUNJUElFTlQiPm5hbWVAZm9vLmNvbTwvRVhURU5TSU9OPgogICAgICAgICAgICA8RVhURU5TSU9OIFRZUEU9IkVNQUlMIiBTVEFLRT0iVFJBTlNQT1JURVIiPm5hbWVAYmFyLmNvbTwvRVhURU5TSU9OPgogICAgICAgICAgICA8RVhURU5TSU9OIFRZUEU9IkRJVklTSU9OIiBTVEFLRT0iVFJBTlNQT1JURVIiPtCX0LDQutCw0YDQv9Cw0YLRgdGM0LrQtSDQstGW0LTQtNGW0LvQtdC90L3RjyBOMTwvRVhURU5TSU9OPgogICAgICAgICAgICA8RVhURU5TSU9OIFRZUEU9IktPQVRVVSIgU1RBS0U9IlJFQ0lQSUVOVCIgVklTSUJMRT0iSU5WSVNJQkxFIj7Ql9Cw0LrQsNGA0L/QsNGC0YHRjNC60LUg0LLRltC00LTRltC70LXQvdC90Y8gTjE8L0VYVEVOU0lPTj4KICAgICAgICAgICAgPEVYVEVOU0lPTiBUWVBFPSJCQVJDT0RFIiBTVEFLRT0iT1JJR0lOQVRPUiIgQ0FSR09fUk9XTlVNPSIxIj4xMjM0NTY3ODwvRVhURU5TSU9OPgogICAgICAgIDwvREVDTEFSRVhUPgogICAgPC9TSUdOX0VOVkVMT1BFPgogICAgPFNJR05BVFVSRSBPV05FUj0iT1JJR0lOQVRPUiI+VDFKSlIwbE9RVlJQVWw5VFNVZE9SVVFLPC9TSUdOQVRVUkU+CjwvREVDTEFSPgo=\"\n}")).asJson
//      .check(jsonPath("$.id").saveAs("ettId"))
//    ).exec(http("Get Ettn")
//      .get("/ettn/" + "${ettId}")
//      .header("Authorization", "Bearer "+ "${jwt}")
//      .check(jsonPath("$.stage"))
    //)
      //.pause(1)

  setUp(
//    scan.inject(constantUsersPerSec(2) during(60 seconds))
    scan.inject(rampUsersPerSec(1) to 7 during (10 minutes) randomized)
  ).protocols(httpConf)
}