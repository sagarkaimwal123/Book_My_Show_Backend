package com.example.BookMyShow3.Service;

import com.example.BookMyShow3.Dtos.UserRequestDto;
import com.example.BookMyShow3.Models.UserEntity;
import com.example.BookMyShow3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String createUser(UserRequestDto userRequestDto)
    {
        UserEntity userEntity=UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();
        try{
            userRepository.save(userEntity);
        }catch(Exception e)
        {
            return "user couldnt be added";
        }
        return "User added be Successfully";
    }


}
