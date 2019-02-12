package practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.domain.Template;
import practice.service.BasicService;

import java.util.List;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @Autowired
    BasicService basicService;

    @RequestMapping(value = "/list")
    public ResponseEntity list() {
        List<Template> templates = basicService.list(Template.class);
        return ResponseEntity.ok(templates);
    }
}
