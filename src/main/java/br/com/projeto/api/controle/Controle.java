package br.com.projeto.api.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Usuario;
import br.com.projeto.api.repositorio.Repositorio;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*")
public class Controle {
    
    @Autowired
    private Repositorio acao;

@PostMapping("/")
public ResponseEntity<?> cadastrar(@RequestBody @Valid Usuario usuario) {
    try {
        Usuario salvo = acao.save(usuario);
        return ResponseEntity.ok(salvo);
    } catch (ConstraintViolationException e) {
        boolean erroEmail = e.getConstraintViolations().stream()
            .anyMatch(cv -> cv.getPropertyPath().toString().equals("email") &&
                            cv.getMessage().contains("bem formado"));

        if (erroEmail) {
            return ResponseEntity.badRequest().body("Email no formato inválido");
        }

        return ResponseEntity.badRequest().body("Erro de validação nos dados enviados.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
}

    @GetMapping("/")
    public Iterable<Usuario> selecionar(){
        return acao.findAll();
    }

    @PutMapping("/")
    public ResponseEntity<?> editar(@RequestBody @Valid Usuario usuario) {
        try {
            Usuario atualizado = acao.save(usuario);
            return ResponseEntity.ok(atualizado);
        } catch (ConstraintViolationException e) {
            boolean erroEmail = e.getConstraintViolations().stream()
                .anyMatch(cv -> cv.getPropertyPath().toString().equals("email") &&
                                cv.getMessage().contains("bem formado"));
    
            if (erroEmail) {
                return ResponseEntity.badRequest().body("Email no formato inválido");
            }
    
            return ResponseEntity.badRequest().body("Erro de validação nos dados enviados.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }

    
    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id){
        acao.deleteById(id);
    }
}

