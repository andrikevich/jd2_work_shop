package it.academy.rest;

import it.academy.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import it.academy.service.VisitorService;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class VisitorController {

    @Autowired
    VisitorService visitorService;



@PutMapping("/visitor_count")
public void plusVisitor(){
    visitorService.saveVisitor();
}

@GetMapping("/visitor_count")
    public int readCount (){
    return visitorService.getMaxVisitor();
}
}
