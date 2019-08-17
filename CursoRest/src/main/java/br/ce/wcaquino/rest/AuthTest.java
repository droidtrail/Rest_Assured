package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

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
	
	@Test
	public void naoDeveAcessarSemSenha() {
	given()
		.log().all()
	.when()
		.get("http://restapi.wcaquino.me/basicauth")
	.then()
		.log().all()
		.statusCode(401)	
		;
	}
	
	@Test
	public void deveFazerAutenticacaoBasica() {
	given()
		.log().all()
	.when()
		.get("http://admin:senha@restapi.wcaquino.me/basicauth")
	.then()
		.log().all()
		.statusCode(200)
		.body("status", is("logado"))
		;
	}
	
	@Test
	public void deveFazerAutenticacaoBasica2() {
	given()
		.log().all()
		.auth().basic("admin", "senha")
	.when()
		.get("http://restapi.wcaquino.me/basicauth")
	.then()
		.log().all()
		.statusCode(200)
		.body("status", is("logado"))
		;
	}
	
	@Test
	public void deveFazerAutenticacaoBasicaChallenger() {
		given()
			.log().all()
			.auth().preemptive().basic("admin", "senha")
			.when()
		.get("http://restapi.wcaquino.me/basicauth2")
		.then()
			.log().all()
			.statusCode(200)
			.body("status", is("logado"))
		;
	}
	
	@Test
	public void deveFazerAutenticacaoComTokenJWT() {
		Map<String, String> login = new HashMap<String, String>();
			login.put("email", "leandro.nares@gmail.com");
			login.put("senha", "123");
			
		//Login na API e Receber o token
		String token = given()
			.log().all()
			.body(login)
			.contentType(ContentType.JSON)
		.when()
			.post("http://barrigarest.wcaquino.me/signin")
		.then()
			.log().all()
			.statusCode(200)
			.extract().path("token");
				
		//Obter as contas
		given()
			.log().all()
			.header("Authorization","JWT "+ token)
		.when()
			.get("http://barrigarest.wcaquino.me/contas")
		.then()
			.log().all()
			.statusCode(200) 
			.body("nome", hasItem("Conta Teste Rest Assured")) //hasItem para uma coleção de contas
		;
	}	
}
