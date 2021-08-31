package com.elk.elasticsearchlogstashkibana.controller;

import com.elk.elasticsearchlogstashkibana.model.User;
import com.elk.elasticsearchlogstashkibana.payload.request.UserCreateRequest;
import com.elk.elasticsearchlogstashkibana.payload.request.UserUpdateRequest;
import com.elk.elasticsearchlogstashkibana.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {



    final UserService userService;




    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @PostMapping(value = "/user")
    @Operation(summary = "Create New User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User inserted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User NotFound",
                    content = @Content),
            @ApiResponse(responseCode = "500",description = "Internal Server Error")})
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }
    @PutMapping("/user/{id}")

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "A user has been updated",
                    content = {@Content(mediaType = "application/json")}
            )
    }
    )
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@NotBlank @PathVariable("id") int id, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(id, userUpdateRequest);
    }


    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);


    }

    @GetMapping("/user/{id}")
    public Optional<User> findUser(@PathVariable("id") int id){



       return userService.getUser(id);

    }

    @GetMapping(value = "/exception")
    public String exception() {
        String response = "";
        try {
            throw new Exception("Exception has occured....");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            response =e.getMessage();
        }

        return response;
    }

}
