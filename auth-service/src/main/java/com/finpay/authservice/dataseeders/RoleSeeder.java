package com.finpay.authservice.dataseeders;

import com.finpay.authservice.models.Role;
import com.finpay.authservice.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleSeeder {
    @Bean
    public CommandLineRunner seedRoles(RoleRepository roleRepository) {
        return args -> {
            if(roleRepository.findByRoleName("USER").isEmpty()){
                roleRepository.save(new Role(null, "USER"));
            }
            if(roleRepository.findByRoleName("ADMIN").isEmpty()){
                roleRepository.save(new Role(null, "ADMIN"));
            }
        };
    }
}
