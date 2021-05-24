package br.com.resultatec.sagflix.domain.exception;

public class EntidadeNaoEncontraException extends RuntimeException{
    public static final long serialVersionUID = 1L;
    
    public EntidadeNaoEncontraException(String mensagem) {
        super(mensagem);
    }
}
