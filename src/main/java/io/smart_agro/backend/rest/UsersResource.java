package io.smart_agro.backend.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.smart_agro.backend.domain.UserCredentials;
import io.smart_agro.backend.domain.Users;
import io.smart_agro.backend.model.UsersDTO;
import io.smart_agro.backend.service.UsersService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/userss", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersResource {

    private final UsersService usersService;

    public UsersResource(final UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUserss() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UsersDTO> getUsers(@PathVariable(name = "userId") final Long userId) {
        return ResponseEntity.ok(usersService.get(userId));
    }

  @GetMapping("/username/{username}")
    public ResponseEntity<UsersDTO> getUsers(@PathVariable(name = "username") final String username) {
        return ResponseEntity.ok(usersService.getbyUsername(username));
    }

@PostMapping("/password/{id}/{pass}/{action}")
public ResponseEntity<String> getUsers(@PathVariable(name = "id") Long id, @PathVariable(name = "pass") String pass, @PathVariable(name = "action") String action) {
    UsersDTO user = usersService.get(id);

    if (action.equalsIgnoreCase("verify")) {
        if (usersService.passHashing(pass).equalsIgnoreCase(user.getPassword())) {
            return ResponseEntity.ok("Password match !!");
        } else {
            return new ResponseEntity<>("Password verification failed", HttpStatus.UNAUTHORIZED);
        }
    } else if (action.equalsIgnoreCase("change")) {
        usersService.changePassowrd(usersService.passHashing(pass), id);
        return ResponseEntity.ok("Password changed successfully");
    }

    return new ResponseEntity<>("Invalid action", HttpStatus.UNAUTHORIZED);
}



    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createUsers(@RequestBody @Valid final UsersDTO usersDTO) {
        usersDTO.setPassword(usersService.passHashing(usersDTO.getPassword()));
        final Long createdUserId = usersService.create(usersDTO);
        return new ResponseEntity<>(createdUserId, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Long> updateUsers(@PathVariable(name = "userId") final Long userId,
            @RequestBody  final UsersDTO usersDTO) {
        usersService.update(userId, usersDTO);
        return ResponseEntity.ok(userId);
    }

    @DeleteMapping("/{userId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUsers(@PathVariable(name = "userId") final Long userId) {
        usersService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    
    @PostMapping("/login")
    @ApiResponse(responseCode = "200")
	public ResponseEntity<Object> login(@RequestBody UserCredentials usercre)
			{
		String username = usercre.getUsername();
		String password = usercre.getPassword();
       
           Users user = usersService.validateUser(username, password);
           Map<String, Object> data = new HashMap<String, Object>();
        if(user !=null){

		data.put("Token",usersService.generateUserJWTToken(user));
        data.put("Id",user.getUserId());
        data.put("role",user.getRoleId().getRoleName());
		return new ResponseEntity<>(data, HttpStatus.OK);
       }
       else{
    data.put("message","invald username or password !");
    return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
       }
		
	}

}
