package dam.salesianostriana.dam.GradesAPP.security.errorhandling;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String msg){
        super(msg);
    }
}
