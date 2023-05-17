package utils;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ServiceRest {
    public static Response call(String url, String pathJsonBody, Map<String, String> headers) throws URISyntaxException, IOException {
        RestAssured.defaultParser = Parser.JSON;
        String body = new String(Files.readAllBytes(Paths.get(ServiceRest.class.getResource(pathJsonBody).toURI())));
        return given()
                .headers(headers)
                .body(body)
                .post(url)
                .then()
                .contentType(JSON)
                .extract()
                .response();
    }
}