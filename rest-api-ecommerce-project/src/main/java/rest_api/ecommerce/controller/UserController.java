package rest_api.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rest_api.ecommerce.model.UserRequestRegister;
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

}
