package ru.job4j.accidents;

import net.jcip.annotations.ThreadSafe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ThreadSafe
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
/*        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("secret");
        System.out.println(pwd);*/
        SpringApplication.run(Main.class, args);
    }
}
