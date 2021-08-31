package com.elk.elasticsearchlogstashkibana.service;

import com.elk.elasticsearchlogstashkibana.exception.UserAlreadyExistsException;
import com.elk.elasticsearchlogstashkibana.exception.UserNotFoundException;
import com.elk.elasticsearchlogstashkibana.repository.UserRepository;
import com.elk.elasticsearchlogstashkibana.model.User;
import com.elk.elasticsearchlogstashkibana.payload.request.UserCreateRequest;
import com.elk.elasticsearchlogstashkibana.payload.request.UserUpdateRequest;
import com.elk.elasticsearchlogstashkibana.payload.response.UserDeleteResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {



    final UserRepository userRepository;

    final ModelMapper modelMapper;


    @Override
    public Optional<User> getUser(int id) {

        if (!getUsers().isEmpty()){
            log.info("Request for the id =[" + id + "]");
            log.info(userRepository.findById(id)+" data selected");
        }



         return userRepository.findById(id);
    }

    @Override
    public User createUser(UserCreateRequest userCreateRequest) {

        User user=modelMapper.map(userCreateRequest,User.class);

        log.info(userRepository.save(user)+"Added");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, UserUpdateRequest userUpdateRequest) {
        log.info("Selected User:"+userRepository.findById(id));
        User newUser=userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        newUser.setFirstName(userUpdateRequest.getFirstName());
        newUser.setLastName(userUpdateRequest.getLastName());
        log.info(userRepository.save(newUser)+" Updated");

        return userRepository.save(newUser);
    }

    @Override
    public UserDeleteResponse deleteUser(int id) {
        User user=userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        log.info(userRepository.findById(id)+" Deleted");
        userRepository.delete(user);



        return new UserDeleteResponse(id+" User deleted");
    }

    @Override
    public List<User> getUsers() {

        Iterable<User> result = userRepository.findAll();
        List<User> userList = new ArrayList<User>();
        result.forEach(userList::add);
        return userList;
    }
}
