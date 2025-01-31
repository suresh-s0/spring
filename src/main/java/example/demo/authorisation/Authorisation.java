package example.demo.authorisation;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class Authorisation {

    private static final String SecretKey="javajwtoken"; 
    private static final long EXPIRATION_TIME = 1000 * 60 * 3660; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SecretKey.getBytes());

// generat token 
    public String generateToken(Long id){
       String token=Jwts.builder().setSubject(String.valueOf(id))
                    .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                    .signWith(key,SignatureAlgorithm.HS256).compact();

       return token;
    }

    // getUserId
    public Long getUserByToken(String token){
        Long id=Long.parseLong(Jwts.parserBuilder()
                    .setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject());
       
        return id;
    }


    public boolean validateToken(String token, Long userId) {
        return userId.equals(getUserByToken(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return Jwts.parserBuilder().setSigningKey(key)
        .build().parseClaimsJws(token).getBody()
        .getExpiration().before(new Date());
    }
    


}
