package br.com.projeto.api.modelo;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void deveValidarUsuarioComDadosCorretos() {
        Usuario usuario = new Usuario();
        usuario.setNome("Rafael Oliveira");
        usuario.setEmail("rafael@email.com");
        usuario.setSenha("123456");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertTrue(violations.isEmpty(), "Usuário válido não deveria ter violações.");
    }

    @Test
    public void deveDetectarEmailInvalido() {
        Usuario usuario = new Usuario();
        usuario.setNome("Rafael");
        usuario.setEmail("emailinvalido");
        usuario.setSenha("123456");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    public void deveDetectarNomeVazio() {
        Usuario usuario = new Usuario();
        usuario.setNome("");
        usuario.setEmail("rafael@email.com");
        usuario.setSenha("123456");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("nome")));
    }

    @Test
    public void deveDetectarSenhaCurta() {
        Usuario usuario = new Usuario();
        usuario.setNome("Rafael");
        usuario.setEmail("rafael@email.com");
        usuario.setSenha("123");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("senha")));
    }
}