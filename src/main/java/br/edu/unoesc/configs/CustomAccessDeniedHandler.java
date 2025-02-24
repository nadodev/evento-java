package br.edu.unoesc.configs;

import java.io.IOException;
import java.io.OutputStream;

import br.edu.unoesc.exceptions.ExceptionMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ExceptionMessage e = new ExceptionMessage("Access Denied",  false);

        OutputStream out = response.getOutputStream();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, e);
        out.flush();

    }

}