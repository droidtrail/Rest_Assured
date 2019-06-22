package br.ce.wcaquino.rest;

import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.RestAssured;

public class UserXMLTest {
	
	@Test
	public void devoTrabalharComXML() {
		
		RestAssured.given()
			.when()
				.get("https://restapi.wcaquino.me/usersXML/3")
			.then()
				.statusCode(200)
				.rootPath("user")
				.body("name", is("Ana Julia"))
				.body("@id", is("3"))
				
				.rootPath("user.filhos")
				.body("name.size()",is(2))
				
				.detachRootPath("filhos")
				.body("filhos.name[0]", is("Zezinho"))
				.body("filhos.name[1]", is("Luizinho"))
				
				.appendRootPath("filhos")
				.body("name", hasItem("Luizinho"))
				.body("name", hasItems("Luizinho", "Zezinho"))
			;
	}
}
