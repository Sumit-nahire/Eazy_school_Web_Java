package web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("currentUri")
    public String currentUri(HttpServletRequest request) {
        // If you use a context-path later, you may want request.getServletPath() instead.
        return request.getRequestURI();
    }
}