package br.com.projeto.api.controle;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.projeto.api.modelo.Usuario;

@SpringBootTest
@AutoConfigureMockMvc
public class ControleTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCadastrarUsuarioComSucesso() throws Exception {
        String usuarioValido = """
                {
                  "nome": "Rafael",
                  "email": "rafael@email.com",
                  "senha": "senhaSegura123"
                }
                """;

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioValido))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("rafael@email.com"))
                .andDo(print());
    }

@Test
void deveEditarUsuarioComSucesso() throws Exception {
    String usuarioInicial = """
            {
              "nome": "Usuario Original",
              "email": "original@email.com",
              "senha": "senhaOriginal"
            }
            """;

    String responseContent = mockMvc.perform(post("/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(usuarioInicial))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

    com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    Usuario usuarioCriado = mapper.readValue(responseContent, Usuario.class);
    Long idDoUsuarioCriado = usuarioCriado.getId();
    String usuarioValidoParaEdicao = String.format("""
            {
              "id": %d,
              "nome": "Rafael",
              "email": "rafael@email.com",
              "senha": "senhaSegura123"
            }
            """, idDoUsuarioCriado);

    mockMvc.perform(put("/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(usuarioValidoParaEdicao))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email").value("rafael@email.com"))
            .andDo(print());
}

    @Test
    void deveRetornarErroQuandoEmailInvalidoNaAtualizacao() throws Exception {
        String usuarioParaAtualizarInvalido = """
                {
                "id": 1,
                "nome": "T",
                "email": "emailinvalido", 
                "senha": "123"
                }
                """;

        mockMvc.perform(put("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioParaAtualizarInvalido))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andDo(print());
    }

    @Test
    void deveRetornarUsuariosNoSelect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void deveRemoverUsuario() throws Exception {
        long idParaRemover = 1L;

        mockMvc.perform(delete("/" + idParaRemover))
                .andExpect(status().isOk())
                .andDo(print());
    }
}