package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
		//395c91b2a74d671af477b24a59fab403
		//http://api.openweathermap.org/data/2.5/weather?q=Rio%20de%20Janeiro&appid=395c91b2a74d671af477b24a59fab403&&units=metric
		
	}
	
	@Test
	public void deveObterClima() {
		
		given()
		.log().all()
		.queryParam("q","Rio de Janeiro")
		.queryParam("appid","395c91b2a74d671af477b24a59fab403")
		.queryParam("units","metric")
	.when()
		.get("http://api.openweathermap.org/data/2.5/weather")
	.then()
		.log().all()
		.statusCode(200)
		.body("name", is("Rio de Janeiro"))
		.body("coord.lon", is(-43.21f))
		.body("main.temp", greaterThan(25f))
		
		
	;
		
	}

}
