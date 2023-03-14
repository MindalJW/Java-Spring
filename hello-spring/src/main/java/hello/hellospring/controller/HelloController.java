package hello.hellospring.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")//스프링 입문 완
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-templete";
    }

    @GetMapping("hello-string")//API방식 데이터를 그대로 보내줌
    @ResponseBody//
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return  hello;
    }

    static class Hello {
        private String name; //private이라서 바로 못꺼내쓰기때문에 외부라이브러리에서 사용할수있게 getter와 setter사용

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
