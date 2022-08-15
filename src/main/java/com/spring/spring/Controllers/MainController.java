package com.spring.spring.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @RequestMapping("helloworld")
    @ResponseBody
    public String helloWorld(){
        return "Hello World";
    }
    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {

        return "<html>" +
                "<head>" +
                "<title> My First HTML Page - Changed</title>" +
                "</head>" +
                "<body>" +
                "My first html page with body - Changed" +
                "</body>" +
                "</html>";
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloHtmljsp() {

        return "sayhello";
    }
}
