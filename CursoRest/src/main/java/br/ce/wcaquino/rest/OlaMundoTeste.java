package br.ce.wcaquino.rest;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTeste {

	@Test
	public void testOlaMundo() {
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		Assert.assertTrue("O status code deveria ser 200.",response.statusCode() == 200);
		Assert.assertEquals(200,response.statusCode());//Primeiro par�metro: Esperado - Segundo par�metro: o que veio do site no m�todo GET

		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}

}
