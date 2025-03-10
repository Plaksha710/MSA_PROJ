package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDTO).orElse(null);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public void deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }


    // 🔹 Convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUserID(), user.getName(), user.getEmail(), user.getRole());
    }

    private User convertToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getName(),
                userDTO.getPassword(),  // ✅ Pass password correctly
                userDTO.getEmail(),     // ✅ Pass email correctly
                userDTO.getRole()
        );
    }
}
