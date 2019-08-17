package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class AuthTest {
	
	@Test
	public void deveAcessarSWAPI() {
		
		given()
			.log().all()
		.when()
			.get("https://swapi.co/api/people/1")
		.then()
			.log().all()
			.statusCode(200)
			.body("name", is("Luke Skywalker"))
			.body("height",is("172"))
			.body("mass", is("77"))
			.body("hair_color", is("blond"))
			.body("skin_color", is("fair"))
			.body("eye_color", is("blue"))
			.body("birth_year", is("19BBY"))
			.body("gender", is("male"))
			.body("homeworld", is("https://swapi.co/api/planets/1/"))
		;
		
	}

}
