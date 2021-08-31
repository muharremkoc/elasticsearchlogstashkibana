package com.elk.elasticsearchlogstashkibana.service;

import com.elk.elasticsearchlogstashkibana.model.User;
import com.elk.elasticsearchlogstashkibana.payload.request.UserCreateRequest;
import com.elk.elasticsearchlogstashkibana.payload.request.UserUpdateRequest;
import com.elk.elasticsearchlogstashkibana.payload.response.UserDeleteResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
     Optional<User> getUser(int id);
    User createUser(UserCreateRequest userCreateRequest);
    User updateUser(int id, UserUpdateRequest userUpdateRequest);
    UserDeleteResponse deleteUser(int id);
    List<User> getUsers();
}
