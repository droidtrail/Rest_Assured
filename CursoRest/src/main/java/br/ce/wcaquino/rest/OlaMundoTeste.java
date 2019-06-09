package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTeste {

	@Test
	public void testOlaMundo() {
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		assertTrue(response.statusCode() == 200);
		assertTrue("O status code deveria ser 200.", response.statusCode() == 200);
		assertEquals(200, response.statusCode());// Primeiro parâmetro: Esperado - Segundo parâmetro: o que veio
														// do site no método GET

		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}

	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		
		given()//Pré condições
		.when() //Ação
			.get("http://restapi.wcaquino.me/ola")
		.then()//Assertivas
			.statusCode(200);

	}

}
