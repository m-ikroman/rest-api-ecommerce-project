package rest_api.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import rest_api.ecommerce.entity.User;
import rest_api.ecommerce.model.UserRequestRegister;
import rest_api.ecommerce.model.UserResponse;
import rest_api.ecommerce.repository.UserRepository;
import rest_api.ecommerce.security.BCrypt;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void register(UserRequestRegister request){
        validationService.validate(request);

        if (userRepository.existsByUsername(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        userRepository.save(user);

    }

}
