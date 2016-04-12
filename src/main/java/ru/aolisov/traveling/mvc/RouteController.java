package ru.aolisov.traveling.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Alex on 3/21/2016.
 */

@Controller
@RequestMapping("")
public class RouteController {

    @RequestMapping("")
    public String openMainPage() {
        return "static/pages/index.html";
    }

    @RequestMapping("/admin")
    public String openAdminPage() {
        return "static/pages/admin.html";
    }

    @RequestMapping("/axisEdit")
    public String openAxisEditPage() {
        return "static/pages/axisEdit.html";
    }

    @RequestMapping("/401")
    public String open401() {
        return "static/pages/401.html";
    }

    @RequestMapping("/404")
    public String open404() {
        return "static/pages/404.html";
    }
}
