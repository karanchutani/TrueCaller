package com.example.demo.controller;

import com.example.demo.model.SpamResponseDTO;
import com.example.demo.responseDTOs.OutputResponseDTO;
import com.example.demo.service.SpamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SpamController {

    /**
     * Spam Controller
     */

    @Autowired
    private SpamService spamService;

    /**
     * @param number
     * @return
     */

    @ApiOperation(value = "Spam a number", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Spam operation is successfull"),
            @ApiResponse(code = 401, message = "You are not authorized to spam this number"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "Resource is not found")
    }
    )
    @PostMapping("/spam")
    public ResponseEntity<OutputResponseDTO> spamContact(@RequestParam(value = "number", required = false) String number) {
        SpamResponseDTO spamResponseDTO = spamService.markNumberAsSpam(number);
        OutputResponseDTO responseDTO = new OutputResponseDTO("Number is spammed successfully ",spamResponseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
