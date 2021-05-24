package br.com.resultatec.sagflix.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.resultatec.sagflix.api.exceptionhandler.Problema.Campo;
import br.com.resultatec.sagflix.domain.exception.EntidadeNaoEncontraException;
import br.com.resultatec.sagflix.domain.exception.NegocioExeption;

@JsonInclude(Include.NON_NULL)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Campo> campos = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach( error -> {
                String nome = ((FieldError) error).getField();
                String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
                campos.add(new Problema.Campo(nome, mensagem));
            }
        );

        Problema problema = getProblema(
            status,
            "Preenchimento.campos.obrigatorio.titulo", 
            campos);

        return super.handleExceptionInternal(ex, problema, headers, status, request);
    } 

    @ExceptionHandler(NegocioExeption.class)
    public ResponseEntity <Object> handlerNegocioException(NegocioExeption ex, WebRequest webRequest) {
     
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = getProblema(status, ex.getMessage(), null);

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(EntidadeNaoEncontraException.class)
    public ResponseEntity<Object> handlerEntidadeNaoEncontraException(EntidadeNaoEncontraException ex, WebRequest webRequest) {
             
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problema problema = getProblema(status, ex.getMessage(), null);

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, webRequest);
    }

    private Problema getProblema(HttpStatus status, String mensagem, List<Campo> campos) {

       // String mensagemTranslater = messageSource.getMessage(mensagem, null, LocaleContextHolder.getLocale());

        return  Problema
        .builder()
        .status(status.value())
        .dataHora(LocalDateTime.now())
        .titulo(mensagem)
        .campos(campos)
        .build();
    }
}
