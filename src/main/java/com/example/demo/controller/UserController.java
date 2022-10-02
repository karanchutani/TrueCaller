package com.example.demo.controller;

import com.example.demo.DTOservice.UserObjectConverterService;
import com.example.demo.model.User;
import com.example.demo.responseDTOs.OutputResponseDTO;
import com.example.demo.responseDTOs.UserResponseDTO;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    /**
     * User Controller
     */

    @Autowired
    private UserService userService;

    @Autowired
    private UserObjectConverterService userObjectConverterService;

    /**
     * @return
     */

    @ApiOperation(value = "Get users", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User List"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "Resource is not found")
    }
    )
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUsers();
        /*Set<UserResponseDTO> userResponseDTOS = userObjectConverterService.convertListOfUsersIntoDTO(users);
        OutputResponseDTO responseDTO = new OutputResponseDTO("User List",userResponseDTOS);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    */
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    /**
     * @param user
     * @return
     */


    @ApiOperation(value = "Create a user along with their personal contacts", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User is created successfully"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "Resource is not found")
    }
    )
    @PostMapping("/user")
    public ResponseEntity<OutputResponseDTO> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        UserResponseDTO userResponseDTO = userObjectConverterService.convertUserObjectToDTO(createdUser);
        OutputResponseDTO responseDTO = new OutputResponseDTO("User is created successfully ",userResponseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}