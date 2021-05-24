package br.com.resultatec.sagflix.domain.exception;

public class NegocioExeption extends RuntimeException {
    

    public static final long serialVersionUID = 1L;
    
    public NegocioExeption(String mensagem) {
        super(mensagem);
    }
}
