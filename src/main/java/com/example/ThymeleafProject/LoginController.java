package com.example.ThymeleafProject;

import com.example.ThymeleafProject.user.User;
import com.example.ThymeleafProject.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/main";
    }

    @GetMapping("/registration")
    public String register() {
        return "/registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@RequestParam("regUsername") String username,
                                  @RequestParam("regPassword") String password, Model model) {
        // Проверка, существует ли пользователь с таким именем
        if (userRepository.findByUsername(username) != null) {
            // Пользователь с таким именем уже существует, необходимо вывести сообщение об ошибке или выполнить другие действия
            // Например, перенаправить пользователя обратно на страницу регистрации с сообщением об ошибке
            model.addAttribute("error", "exists");
            return "/registration"; // Вернуться на страницу входа с параметром ошибки
        }

        // Хеширование пароля перед сохранением
        String hashedPassword = passwordEncoder.encode(password);
        // Создание нового пользователя
        User newUser = new User(username, "{bcrypt}" + hashedPassword, "ROLE_USER");

        // Сохранение нового пользователя в базе данных
        userRepository.save(newUser);

        // После успешной регистрации перенаправить пользователя на страницу входа
        return "redirect:/";
    }
}


