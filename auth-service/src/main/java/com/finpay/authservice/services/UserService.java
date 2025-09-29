package com.finpay.authservice.services;

import com.finpay.authservice.models.Location;
import com.finpay.authservice.models.Role;
import com.finpay.authservice.models.UserEntity;
import com.finpay.authservice.repositories.LocationRepository;
import com.finpay.authservice.repositories.RoleRepository;
import com.finpay.authservice.repositories.UserRepository;
import com.finpay.common.dto.users.CreateUserRequest;
import com.finpay.common.dto.users.UserDTO;
import com.finpay.common.dto.users.UserLocationDTO;
import com.finpay.common.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));;

        // Assuming roles are loaded as a list
        return new CustomUserDetails(userEntity);
    }

    public List<UserLocationDTO> getAllUsersLocation() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private UserLocationDTO convertEntityToDTO(UserEntity user) {
        UserLocationDTO userLocationDTO = new UserLocationDTO();
        userLocationDTO.setUserId(user.getId());
        userLocationDTO.setEmail(user.getEmail());
        userLocationDTO.setPlace(user.getLocation().getPlace());
        userLocationDTO.setLongitude(user.getLocation().getLongitude());
        userLocationDTO.setLatitude(user.getLocation().getLatitude());
        return userLocationDTO;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO convertToDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setRole(RoleEnum.valueOf(user.getRole().getRoleName()));
        userDTO.setLocation(user.getLocation().getPlace());
        return userDTO;
    }

    public UserEntity createUser(CreateUserRequest request) {
        Location location = new Location();
        location.setPlace(request.getLocation());
        location.setDescription("Awesome");
        location.setLongitude(40.5);
        location.setLatitude(38.9);
        locationRepository.save(location);

        Role role = roleRepository.findByRoleName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setEmail(request.getEmail());
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setRole(role);
        userEntity.setLocation(location);

        return userRepository.save(userEntity);
    }
}
