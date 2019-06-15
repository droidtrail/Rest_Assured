package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class UserJsonTest {
	
	@Test
	public void deveVerificarPrimeiroNivel() {
		
		given()
		.when()
			.get("https://restapi.wcaquino.me/users/1")
		.then()
			.statusCode(200)
			.body("id", is(1))
			.body("name", containsString("Silvaa"))
			.body("age", greaterThan(18))
			;
	}
	
	@Test
	public void deveVerificarPrimeiroNivelOutrasFormas() {
		
		Response response = RestAssured.request(Method.GET,"https://restapi.wcaquino.me/users/1");
		
		//Path
		//response.path("id");
		//System.out.println(response.path("id"));
		Assert.assertEquals(new Integer(1), response.path("id"));
		Assert.assertEquals(new Integer(1), response.path("%s","id"));
		
		//Jsonpath
		JsonPath jpath = new JsonPath(response.asString());
		Assert.assertEquals(1, jpath.getInt("id"));
		
		//From
		int id = JsonPath.from(response.asString()).getInt("id");
		Assert.assertEquals(1, id);
		
		
	}
	
}
