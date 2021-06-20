package br.com.resultatec.sagflix.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

 
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@Builder
public class Problema {

    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;

    @Singular(value = "adicionarCampo")
    private List<Campo> campos;

    @AllArgsConstructor
    @Getter
    public static class Campo {
        private String nome;
        private String mensagem;
    }
}
