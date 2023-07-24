package ru.itmentor.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itmentor.spring.boot_security.demo.controller.AdminController;

import ru.itmentor.spring.boot_security.demo.model.User;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SpringBootSecurityDemoApplicationTests {
	//Junit тесты
	@Autowired
	private AdminController adminController;

	@Test
	void testShouldGetUser() {
		User user = adminController.getUser(1);
		assertThat(user).isNotNull();
		assertThat(user.getId()).isEqualTo(1);
	}
	@Test
	void testShouldNewUser() {
		User test = adminController.createUser(new User());
		assertThat(test).isNotNull();
	}
}
