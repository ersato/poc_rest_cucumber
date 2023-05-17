package steps.api;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.ConfigReader;
import utils.ServiceRest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleDetailStepDefinitions {

    private Response response;
    private String url;

    @Given("I want to fetch the information from sale_detail")
    public void setUrl() throws Exception {
        Properties properties = new ConfigReader().getPropValues();
        url = properties.getProperty("urlPnrSaleDetail");
    }

    @When("call the service sale_detail")
    public void callWebService(Map<String, String> headers) throws IOException, URISyntaxException {
        response = ServiceRest.call(url, "/json/bodySaleDetail.json", headers);
    }

    @Then("I should see a {int} status code in response for sale_detail")
    public void iShouldSeeAStatusCodeInResponse(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("expected receive for sale_detail this")
    public void iShouldResponse(Map<String, Object> map) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        map.forEach((attribute, value) ->
                Assert.assertEquals(value.toString(), jsonPathEvaluator.get(attribute).toString())
        );
    }

    @Then("expected receive for sale_detail this {string}")
    public void iShouldResponseJson(String json) throws URISyntaxException, IOException {
        String body = new String(Files.readAllBytes(Paths.get(getClass().getResource(json).toURI())));
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(body), mapper.readTree(response.getBody().prettyPrint()));
    }
}
