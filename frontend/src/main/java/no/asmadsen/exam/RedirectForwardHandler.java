package no.asmadsen.exam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * COPIED - https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/spring/jsf/src/main/java/org/tsdes/intro/spring/jsf/RedirectForwardHandler.java
 */
@Controller
public class RedirectForwardHandler {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String forward() {
        return "forward:index.xhtml";
    }
}