package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

public class VerbosTest {
	
	@Test
	public void deveSalvarUsuario() {
		
		given()
				.log().all()
				.contentType("application/json")
				.body("{\"name\": \"Jose\",\"age\":50}")
		.when()
				.post("https://restapi.wcaquino.me/users")
		.then()
				.log().all()
				.statusCode(201)
				.body("id", is(notNullValue()))
				.body("name", is("Jose"))
				.body("age", is(50))
		;
		
	}

}
