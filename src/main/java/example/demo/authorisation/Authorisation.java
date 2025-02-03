package example.demo.authorisation;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class Authorisation {

    // private static final String SecretKey="javajwtoken"; 
    private static final long EXPIRATION_TIME = 1000 * 60 * 3660; // 1 hour

    // private final Key key = Keys.hmacShaKeyFor(SecretKey.getBytes());
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


// generat token 
    public String generateToken(String name){
       String token=Jwts.builder().setSubject(String.valueOf(name))
                    .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                    .signWith(key,SignatureAlgorithm.HS256).compact();

       return token;
    }

    // getUserId
    public String getUserByToken(String token){
        String name=Jwts.parserBuilder()
                    .setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
       
        return name;
    }


    public boolean validateToken(String token, String name) {
        return name.equals(getUserByToken(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return Jwts.parserBuilder().setSigningKey(key)
        .build().parseClaimsJws(token).getBody()
        .getExpiration().before(new Date());
    }
    


}
