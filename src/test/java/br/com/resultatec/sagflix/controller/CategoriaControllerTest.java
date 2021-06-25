package br.com.resultatec.sagflix.controller;

import br.com.resultatec.sagflix.domain.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.resultatec.sagflix.api.v1.assembler.CategoriaAssembler;
import br.com.resultatec.sagflix.api.v1.controller.CategoriaController;
import br.com.resultatec.sagflix.domain.model.Categoria;
import br.com.resultatec.sagflix.domain.repository.CategoriaRepository;
import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {
    
    //unidade sendo testada
    @Autowired private CategoriaController categoriaController;

    //as outras unidades de codigo nao podem interferir no teste do controller 
    // e serao mockeadas
    @MockBean private BuscarCategoriaService buscarCategoriaService;

    @MockBean private CategoriaRepository categoriaRepository;
    @MockBean private CatalogoCategoriaService catalogoCategoriaService;
    @MockBean private CategoriaAssembler categoriaAssembler;
    @MockBean private InativarCategoriaService inativarCategoriaService;
    @MockBean private AtivarCategoriaService ativarCategoriaService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.categoriaController);
    }

    //o teste deve fazer (comportamento esperado) _ O que vamos fazer
    @Test
    public void deveRetornarSucessoComStatusHTTP200_QuandoBuscarCategoria() {

        Categoria categoriaMock = new Categoria();
        categoriaMock.setCor("#Ffffff");
        categoriaMock.setLink("https://www.resultatec.com.br");

        //dentro da unidade sendo testada esta tento a chamada no repository por isso temos que mockear 
        //evitando a chamada ao banco que SEMPRE vamos evitar
        when(buscarCategoriaService.buscar(1L))
            .thenReturn(categoriaMock);

        given()
            .accept(ContentType.JSON)
            .when()//quando
                .get("/categorias/{categoriaId}", 1L)//obeter do endero
            .then()//entao
                .statusCode(HttpStatus.OK.value());//responda com ok 200
    }

    @Test
    public void deveRetornarNaoEncontradoComStatus404_QuandoBuscarCategoria() {
        when(buscarCategoriaService.buscar(5L))
                .thenReturn(null);

        given()
                .accept(ContentType.JSON)
                .when()
                    .get("/categorias/{categoriaId}", 5L)
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarBadResponse_QuandoBuscarCategoria() {
        given()
                .accept(ContentType.JSON)
                .when()
                    .get("/categorias/{categoriaId}", -1)
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

        verify(this.buscarCategoriaService, never()).buscar(-1L);
    }

}
