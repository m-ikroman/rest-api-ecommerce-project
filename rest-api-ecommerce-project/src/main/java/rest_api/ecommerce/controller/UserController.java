package rest_api.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest_api.ecommerce.entity.User;
import rest_api.ecommerce.model.UpdateUserRequest;
import rest_api.ecommerce.model.UserRequestRegister;
import rest_api.ecommerce.model.UserResponse;
import rest_api.ecommerce.model.WebResponse;
import rest_api.ecommerce.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody UserRequestRegister request){
        userService.register(request);

        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> getUser(User user){
        UserResponse response = userService.getUser(user);

        return WebResponse.<UserResponse>builder().data(response).build();
    }

    @PatchMapping(
            path = "/api/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> updateUser(User user, @RequestBody UpdateUserRequest request){
        UserResponse response = userService.updateUser(user, request);

        return WebResponse.<UserResponse>builder().data(response).build();
    }

}
