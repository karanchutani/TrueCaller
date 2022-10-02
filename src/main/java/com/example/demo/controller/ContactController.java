package com.example.demo.controller;

import com.example.demo.DTOservice.ContactObjectConverterService;
import com.example.demo.responseDTOs.OutputResponseDTO;
import com.example.demo.responseDTOs.SearchResponseDTO;
import com.example.demo.service.PersonalContactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts/search")
public class ContactController {

    /**
     * Contact Controller
     */

    @Autowired
    private ContactObjectConverterService contactObjectConverterService;
    @Autowired
    private PersonalContactService personalContactService;

    /**
     * @param name
     * @return
     */


    @ApiOperation(value = "Lists of users by name starts with or containing but not start with", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User List"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "Resource is not found")
    }
    )
    @GetMapping("/name/{name}")
    public ResponseEntity<OutputResponseDTO> getAllUsersByNameContains(@PathVariable(value = "name", required = false) String name) {
        List<SearchResponseDTO> contacts = personalContactService.findUsersWithNameContaining(name);
        OutputResponseDTO responseDTO = new OutputResponseDTO("Total users by given name are ",contacts);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param number
     * @return
     */


    @ApiOperation(value = "Lists of users by Phone number", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User List"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "Resource is not found")
    }
    )
    @GetMapping("/number/{number}")
    public ResponseEntity<OutputResponseDTO> getAllUsersByNumber(@PathVariable(value = "number", required = false) String number) {
        List<SearchResponseDTO> contacts = personalContactService.findUsersWithNameContainingNumber(number);
        OutputResponseDTO responseDTO = new OutputResponseDTO("Total users by given number are ",contacts);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}