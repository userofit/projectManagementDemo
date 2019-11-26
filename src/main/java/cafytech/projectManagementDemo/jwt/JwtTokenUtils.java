package cafytech.projectManagementDemo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;


public class JwtTokenUtils {


    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtSpringBoot-2019";
    private static final String ISSUER = "cafyyang"; //revised

    // key for role
    private static final String ROLE_CLAIMS = "role";

    // last 3600 seconds = 1hr
    public static final long EXPIRATION = 60L;

    //rememeber Me 7 days, then expired
    public static final long EXPIRATION_REMEMBER =  3600L; //604800L;

    //create token - using json object to contain username and role
    public static String createToken(String username, String role, boolean isRememberMe) {

        // if isRememberMe = true, expiration = EXPIRATION_REMEMBER, otherwise = EXPIRATION
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;

        HashMap<String, Object> map = new HashMap<>();

        map.put(ROLE_CLAIMS, role);

        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(map) //put before setExpiration()
                .setIssuer(ISSUER)
//                .signWith(SignatureAlgorithm.HS512, salt)// not using public/private key pair
//                .signWith(SignatureAlgorithm.RS256, privateKey)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();

        return token;
    }


    // get user name from token
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    // get user role from token
    public static String getUserRole(String token){
        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }

    // check whether expired or not
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
