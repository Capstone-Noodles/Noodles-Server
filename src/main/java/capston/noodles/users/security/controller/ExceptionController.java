package capston.noodles.users.security.controller;

import capston.noodles.common.response.ResponseMessage;
import capston.noodles.users.security.exception.AccessDeniedException;
import capston.noodles.users.security.exception.CAuthenticationEntryPointException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public ResponseMessage entrypointException() {
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping("/accessDenied")
    public ResponseMessage accessDeniedException(){
        throw new AccessDeniedException();
    }
}
