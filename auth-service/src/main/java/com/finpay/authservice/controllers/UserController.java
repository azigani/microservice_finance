package com.finpay.authservice.controllers;

import java.net.URI;
import java.util.List;

import com.finpay.authservice.models.UserEntity;
import com.finpay.authservice.services.UserService;
import com.finpay.common.dto.users.CreateUserRequest;
import com.finpay.common.dto.users.UserDTO;
import com.finpay.common.dto.users.UserLocationDTO;
import com.finpay.common.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/auth-services")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users-location")
    public List<UserLocationDTO> getAlLUsersLocation() {
        return userService.getAllUsersLocation();
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest request) {
        UserEntity user = userService.createUser(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(RoleEnum.valueOf(user.getRole().getRoleName()));
        userDTO.setLocation(user.getLocation().getPlace());

        return ResponseEntity.created(location).body(userDTO);
    }
}
