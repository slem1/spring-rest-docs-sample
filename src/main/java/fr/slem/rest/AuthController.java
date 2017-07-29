package fr.slem.rest;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.API_ROOT_RESOURCE)
public class AuthController {

    public static final String API_ROOT_RESOURCE = "/auth";

    public static final String TOKEN_RESOURCE = "/token";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @GetMapping(TOKEN_RESOURCE)
    public String token(@RequestHeader(name = AUTHORIZATION_HEADER) String header) {
        Assert.notNull(header, "header is missing");
        return "123456";
    }
}
