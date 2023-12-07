package com.ggomezr.bookingsystem.application.service;

import com.ggomezr.bookingsystem.domain.dto.UserDto;
import com.ggomezr.bookingsystem.domain.entity.User;
import com.ggomezr.bookingsystem.domain.exceptions.UserNotFoundException;
import com.ggomezr.bookingsystem.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record UserService(UserRepository userRepository){

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    public void createUser(UserDto userDto) {
        User user = User.builder()
                        .firstName(userDto.firstName())
                        .lastName(userDto.lastName())
                        .email(userDto.password())
                        .password(userDto.password())
                        .enable(userDto.enable())
                        .role(userDto.role())
                        .build();
        userRepository.save(user);
    }

    public void updateUser(User user) throws UserNotFoundException {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        userRepository.save(existingUser);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

}
