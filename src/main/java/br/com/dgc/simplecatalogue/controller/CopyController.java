package br.com.dgc.simplecatalogue.controller;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import br.com.dgc.simplecatalogue.service.impl.CopyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("${spring.data.rest.base-path}/copies")
public class CopyController {
    private final CopyServiceImpl service;

    public CopyController(CopyServiceImpl service){
        this.service = service;
    }

    // Create
    @PostMapping("")
    public ResponseEntity<Copy> create(@Valid @RequestBody Copy copy){
        return ResponseEntity.ok(service.create(copy));
    }
    // Read
    @GetMapping("")
    public ResponseEntity<List<Copy>> read(){
        return ResponseEntity.ok(service.read());
    }
    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Copy> update(@Valid @PathVariable Long id, @Valid @RequestBody Copy copy){
        if(service.readById(id) == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Copy());
        return ResponseEntity.ok(service.create(copy));
    }
    // Delete
    @DeleteMapping("")
    public ResponseEntity<Copy> delete(){
        service.delete();
        return ResponseEntity.ok(new Copy());
    }
}
