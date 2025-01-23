package br.com.jpslg.transito.api.exceptionHandler;

import br.com.jpslg.transito.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);

        problemDetail.setType(URI.create("https://jonathanApi/erros/campos-invalidos"));
        problemDetail.setTitle("Um ou mais campos estão inválidos!!");

        //DEIXANDO EXPLICITO PARA O USER QUAL O CAMPO INVALIDO
        Map<String, String> fields = exception.getBindingResult().getAllErrors()
                .stream()
                .collect(
                        Collectors.toMap(
                                objectError -> ((FieldError) objectError).getField(),
                                objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())
                        )
                );

        problemDetail.setProperty("campos", fields);

        return super.handleExceptionInternal(exception, problemDetail, headers, status, request);
    }


    @ExceptionHandler(NegocioException.class)
    public ProblemDetail handleNegocio(NegocioException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setType(URI.create("https://jonathanApi/erros/regra-de-negocio"));
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleNegocio(DataIntegrityViolationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        problemDetail.setType(URI.create("https://jonathanApi/erros/recurso-em-uso"));
        problemDetail.setTitle("Recurso está em uso");

        return problemDetail;
    }


}