Feature: Search information Sale Detail

  Scenario: Validate response information from sale_detail
    Given I want to fetch the information from sale_detail
    When call the service sale_detail
      | X-Client-Id  | 3578784305991029 |
      | X-Caller-Id  | 1177794367       |
      | Content-Type | application/json |
      | X-Scope      | test             |
    Then I should see a 200 status code in response for sale_detail
    Then expected receive for sale_detail this
      | resource_type | shipment    |
      | resource_id   | 42139118821 |

  Scenario: Validate response information by json
    Given I want to fetch the information from sale_detail
    When call the service sale_detail
      | X-Client-Id  | 3578784305991029 |
      | X-Caller-Id  | 1177794367       |
      | Content-Type | application/json |
      | X-Scope      | test             |
    Then expected receive for sale_detail this "/json/responseSaleDetail.json"