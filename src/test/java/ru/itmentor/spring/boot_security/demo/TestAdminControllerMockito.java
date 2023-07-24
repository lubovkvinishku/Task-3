package ru.itmentor.spring.boot_security.demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.itmentor.spring.boot_security.demo.configs.WebSecurityConfig;
import ru.itmentor.spring.boot_security.demo.controller.AdminController;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TestAdminControllerMockito {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @SneakyThrows
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void getUserById() {
        long id = 1L;
        User user = new User();
        user.setId(id);
        Mockito.when(userService.getById(id)).thenReturn(user);
        mockMvc.perform(get("/admin/getuser/{id}", id))
                .andExpect(status().isOk());
    }
    @SneakyThrows
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void getNotFoundId() {
        long falseId = 66666666L;
        User user = new User();
        user.setId(falseId);

        Mockito.when(userService.getById(falseId)).thenReturn(user);
        mockMvc.perform(get("/admin/getuser/{id}", "abc"))
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void createUser(){
        User user = new User();
        user.setId(100L);
        user.setPass("123");
        Mockito.when(userService.addUser(user)).thenReturn(user);

        mockMvc.perform(post("/admin/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void updateUser() {
        User user = new User();
        user.setId(100L);
        user.setPass("123");
        Mockito.when(userService.editUser(user)).thenReturn(user);

        mockMvc.perform(put("/admin/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void deleteUserById() throws Exception{
        User user = new User();
        user.setId(100L);
        user.setPass("123");

        Mockito.doNothing().when(userService).deleteUser(100L);
        mockMvc.perform(delete("/admin/delete/{id}", 100L))
                .andExpect(status().isOk());
        Mockito.verify(userService, Mockito.times(1)).deleteUser(100L);



    }
}

