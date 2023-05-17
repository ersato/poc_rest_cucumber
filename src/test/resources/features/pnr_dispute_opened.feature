Feature: Search PNR information Dispute Opened

  Scenario: Validate response information from pnr_dispute_opened
    Given I want to fetch the information from pnr_dispute_opened
    When call the service
      | X-Client-Id       | 3578784305991029  |
      | X-Caller-Id       | 703783196         |
      | Content-Type      | application/json  |
      | X-Ppc-Contract    | purchase-detail   |
    Then I should see a 200 status code in response
    Then expected receive this
      | resource_type     | shipment      |
      | resource_id       | 42139118821   |

  Scenario: Validate response information by json
    Given I want to fetch the information from pnr_dispute_opened
    When call the service
      | X-Client-Id       | 3578784305991029  |
      | X-Caller-Id       | 703783196         |
      | Content-Type      | application/json  |
      | X-Ppc-Contract    | purchase-detail   |
    Then expected receive this "/json/responseDisputeOpened.json"