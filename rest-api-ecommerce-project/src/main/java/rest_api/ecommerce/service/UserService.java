package rest_api.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import rest_api.ecommerce.entity.User;
import rest_api.ecommerce.model.UpdateUserRequest;
import rest_api.ecommerce.model.UserRequestRegister;
import rest_api.ecommerce.model.UserResponse;
import rest_api.ecommerce.repository.UserRepository;
import rest_api.ecommerce.security.BCrypt;

import java.util.Objects;
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

    @Transactional(readOnly = true)
    public UserResponse getUser(User user){
        return UserResponse
                .builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    @Transactional
    public UserResponse updateUser(User user, UpdateUserRequest request){
        validationService.validate(request);

        if (Objects.nonNull(request.getName())){
            user.setName(request.getName());
        }

        if (Objects.nonNull(request.getEmail())){
            user.setEmail(request.getEmail());
        }

        if (Objects.nonNull(request.getPhone())){
            user.setPhone(request.getPhone());
        }

        if (Objects.nonNull(request.getPassword())){
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        userRepository.save(user);

        return UserResponse
                .builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

}
