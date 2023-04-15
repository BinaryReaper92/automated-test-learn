package utilities;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HttpRequests {
    public static Response PostAPI(String url, String isLogin, String requestBody, String requestUrl) {
        Header header1 = new Header("Content-type", "application/json");
        Headers headers = null;
        if (isLogin.equals("logged out")){
            headers = new Headers(header1);
        }
        else {
            Header header2 = new Header("Authorization","Bearer " + JSONReader.getRequestBody("src/main/resources/bearerToken.txt"));
            headers = new Headers(header1,header2);
        }
        RestAssured.baseURI = url;
        Response currentResponse = (Response)
                given ()
                        .headers(headers)
                        .and()
                        .body(requestBody)
                        .when()
                        .post(requestUrl)
                        .then()
                        .extract().response();

        if(isLogin.equals("logged out")){
            JsonPath jp = currentResponse.jsonPath();
            JSONReader.bearerTokenWriter((String) jp.get("access_token"));
        }

        return currentResponse;
    }

    public static Response GetAPI(String url, String isLogin, String requestUrl) {
        Header header1 = new Header("Content-type", "application/json");
        Header header2 = new Header("Authorization","Bearer " + JSONReader.getRequestBody("src/main/resources/bearerToken.txt"));
        Headers headers = new Headers(header1,header2);

        RestAssured.baseURI = url;
        Response currentResponse = given()
                .headers(headers)
                .when()
                .get(requestUrl)
                .then()
                .extract()
                .response();

        return currentResponse;
    }

    public static Response PutAPI(String url, String isLogin, String requestBody, String requestUrl) {
        Header header1 = new Header("Content-type", "application/json");
        Header header2 = new Header("Authorization","Bearer " + JSONReader.getRequestBody("src/main/resources/bearerToken.txt"));
        Headers headers = new Headers(header1,header2);

        RestAssured.baseURI = url;
        Response currentResponse = given()
                .headers(headers)
                .and()
                .body(requestBody)
                .when()
                .put(requestUrl)
                .then()
                .extract()
                .response();

        return currentResponse;
    }

    public static Response DeleteAPI(String url, String isLogin, String requestBody, String requestUrl) {
        Header header1 = new Header("Content-type", "application/json");
        Header header2 = new Header("Authorization","Bearer " + JSONReader.getRequestBody("src/main/resources/bearerToken.txt"));
        Headers headers = new Headers(header1,header2);

        RestAssured.baseURI = url;
        Response currentResponse = given()
                .headers(headers)
                .and()
                .body(requestBody)
                .when()
                .delete(requestUrl)
                .then()
                .extract()
                .response();

        return currentResponse;
    }

}
