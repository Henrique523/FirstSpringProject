package com.schoolofnet.SpringMaven;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HelloWorld
{
    @GetMapping
    public String sayHello()
    {
        return "Hello from SpringBoot by School of net";
    }

    @GetMapping("/subpath")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
//    public String subPath(@RequestParam("name") String name)
    public String subPath(final WebRequest webRequest)
    {
        String name = webRequest.getParameter("name");

        if (name != null ) {
            return "This is one subPath of endpoint /" + name;
        }

        return "No query params";
    }

    @GetMapping("/optionalParam")
    public String subPath(@RequestParam(value = "name", required = false) String name)
    {
        if (name != null ) {
            return "This is one optional of endpoint /" + name;
        }

        return "No query params";
    }

    @GetMapping("/{dynamic}")
    public String dynamicSubPath(@PathVariable("dynamic") String name)
    {
        return "Hello " + name + " this is my route name";
    }

    @PostMapping("/post")
    public String sayHelloPost(@RequestBody Map<String, Object> payload)
    {
        return payload.get("name").toString();
    }
}
