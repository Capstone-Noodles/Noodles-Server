package capston.noodles.common;

import capston.noodles.Follow.exception.DuplicateFollowException;
import capston.noodles.common.response.ResponseMessage;
import capston.noodles.users.exception.DuplicatedIdException;
import capston.noodles.users.security.exception.AccessDeniedException;
import capston.noodles.users.security.exception.CAuthenticationEntryPointException;
import capston.noodles.users.exception.LoginIdNotFoundException;
import capston.noodles.users.exception.LoginPwdNotCorrectException;
import capston.noodles.users.security.exception.CRefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseMessage authenticationEntrypointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return new ResponseMessage("토큰이 유효하지 않습니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseMessage accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return new ResponseMessage("해당 리소스를 요청할 권한이 없습니다.");
    }

    @ExceptionHandler(LoginIdNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseMessage loginIdNotFoundException(HttpServletRequest request, LoginIdNotFoundException e) {
        return new ResponseMessage<>("입력하신 Id와 일치하는 아이디가 없습니다.");
    }

    @ExceptionHandler(LoginPwdNotCorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseMessage loginPwdNotCorrectException(HttpServletRequest request, LoginPwdNotCorrectException e) {
        return new ResponseMessage("비밀번호가 일치하지 않습니다.");
    }

    @ExceptionHandler(CRefreshTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseMessage refreshTokenException(HttpServletRequest request, CRefreshTokenException e) {
        return new ResponseMessage(e.getMessage());
    }

    @ExceptionHandler(DuplicatedIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseMessage duplicatedIdException(HttpServletRequest request, DuplicatedIdException e) {
        return new ResponseMessage(e.getMessage());
    }

    @ExceptionHandler(DuplicateFollowException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseMessage duplicateFollowException(HttpServletRequest request, DuplicateFollowException e) {
        return new ResponseMessage(e.getMessage());
    }
}
