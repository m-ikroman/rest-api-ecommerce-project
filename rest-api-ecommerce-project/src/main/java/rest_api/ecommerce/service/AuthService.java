package rest_api.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import rest_api.ecommerce.entity.User;
import rest_api.ecommerce.model.LoginRequest;
import rest_api.ecommerce.model.TokenResponse;
import rest_api.ecommerce.repository.UserRepository;
import rest_api.ecommerce.security.BCrypt;

import java.util.Objects;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public TokenResponse login(LoginRequest request){

        validationService.validate(request);

        User user = userRepository.findFirstByUsername(request.getUsername())
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Username or Password Wrong");
                });

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())){
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);
            return TokenResponse.builder().token(user.getToken()).tokenExpiredAt(user.getTokenExpiredAt()).build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Username or Password Wrong");
        }

    }

    private Long next30Days(){
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }

    @Transactional
    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);
    }

}
