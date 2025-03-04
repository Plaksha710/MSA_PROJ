package com.example.service;

import com.example.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);  // ✅ Changed return type to UserDTO
    UserDTO saveUser(UserDTO userDTO);
    void deleteUser(int id);
}
