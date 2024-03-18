package ru.cherkashin.test.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cherkashin.test.dto.FieldDTO;
import ru.cherkashin.test.services.TestFieldService;

import java.util.List;

@RestController
public class TestFieldController {

    private final TestFieldService testFieldService;

    public TestFieldController(TestFieldService testFieldService) {
        this.testFieldService = testFieldService;
    }

    @PostMapping("/post/{count}")
    public ResponseEntity<String> createField(@PathVariable int count){
        testFieldService.addValueOnCount(count);
        return ResponseEntity.status(200)
                .body(String.format("You creating %s rows", count));
    }

    @GetMapping("/get")
    public ResponseEntity<List<FieldDTO>> getField(){
        List<FieldDTO> listFields = testFieldService.getAllFields();
        return ResponseEntity.status(200)
                .body(listFields);
    }
}
