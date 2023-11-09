package io.smart_agro.backend.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Base64;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.smart_agro.backend.config.AuthException;
import io.smart_agro.backend.config.Constants;
import io.smart_agro.backend.domain.Supervisor;
import io.smart_agro.backend.domain.Users;
import io.smart_agro.backend.model.UsersDTO;
import io.smart_agro.backend.repos.UsersRepository;
import io.smart_agro.backend.util.NotFoundException;

@Service
@Transactional
public class UsersService {

    private  UsersRepository usersRepository;

    public UsersService(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UsersDTO> findAll() {
        final List<Users> userses = usersRepository.findAll(Sort.by("userId"));
        return userses.stream()
                .map(users -> mapToDTO(users, new UsersDTO()))
                .toList();
    }

    public UsersDTO get(final Long userId) {
        return usersRepository.findById(userId)
                .map(users -> mapToDTO(users, new UsersDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UsersDTO getbyUsername(String username) {
        Users user = usersRepository.getByUsername(username);
        if (user != null) {
            return mapToDTO(user, new UsersDTO());
        } else {
            throw new NotFoundException();
        }
    }
    public int changePassowrd(String password,Long userId){
      usersRepository.setchangePasswordForUser(password,userId);
      return 1;
    }
   

//    setchangePasswordForUser

    public Long create(final UsersDTO usersDTO) {
        final Users users = new Users();
        mapToEntity(usersDTO, users);
        return usersRepository.save(users).getUserId();
    }

    public void update(final Long userId, final UsersDTO usersDTO) {
        final Users users = usersRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usersDTO, users);
        usersRepository.save(users);
    }

    public void delete(final Long userId) {
        usersRepository.deleteById(userId);
    }

    private UsersDTO mapToDTO(final Users users, final UsersDTO usersDTO) {
        usersDTO.setUserId(users.getUserId());
        usersDTO.setFullname(users.getFullname());
        usersDTO.setUsername(users.getUsername());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setCooperative(users.getCooperative());
        usersDTO.setCreatedBy(users.getCreatedBy());
        usersDTO.setStatus(users.getStatus());
        usersDTO.setRoleId(users.getRoleId());
        return usersDTO;
    }

    private Users mapToEntity(final UsersDTO usersDTO, final Users users) {
        users.setFullname(usersDTO.getFullname());
        users.setUsername(usersDTO.getUsername());
        users.setPassword(usersDTO.getPassword());
        users.setCooperative(usersDTO.getCooperative());
        users.setCreatedBy(usersDTO.getCreatedBy());
        users.setStatus(usersDTO.getStatus());
        users.setRoleId(usersDTO.getRoleId());
        return users;
    }


    public  String passHashing (String pass) {
		try {
		 MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		 
		 messageDigest.update(pass.getBytes());
		 
		 byte[] resultByteArray = messageDigest.digest();
		 
		 StringBuilder sb = new StringBuilder();
		 
		 for (byte b : resultByteArray) {
		  sb.append(String.format("%02x", b));
		 }
		 return sb.toString();
		 
		} catch (NoSuchAlgorithmException e) {
		//  logger.error("error occured", e);
		}
		
		return "";
	   }

       public String token;
public String generateUserJWTToken(Users user) {
    long timestamp = System.currentTimeMillis();
  
            String encodedKey = Base64.getEncoder().encodeToString(Constants.API_SECRET_KEY.getBytes());
            token = Jwts.builder().signWith(SignatureAlgorithm.HS256,encodedKey)
            .setIssuedAt(new Date(timestamp))
            .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
            .claim("userId", user.getUserId())
            .claim("username", user.getUsername().trim())
            .claim("role", user.getRoleId().getRoleName().trim())
            .compact();
    
    return token;
}
public String generateSupervisorJWTToken(Supervisor user) {
    long timestamp = System.currentTimeMillis();
  
            String encodedKey = Base64.getEncoder().encodeToString(Constants.API_SECRET_KEY.getBytes());
            token = Jwts.builder().signWith(SignatureAlgorithm.HS256,encodedKey)
            .setIssuedAt(new Date(timestamp))
            .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
            .claim("userId", user.getId())
            .claim("username", user.getUsername().trim())
            .claim("role", user.getRoleId().getRoleName().trim())
            .compact();
    
    return token;
}




public Users validateUser(String username, String password) {
    if (username != null)
        username = username.toLowerCase();
    try {
          Users userFound = usersRepository.getByUsername(username);
        if(userFound==null)
        return null;
        if (passHashing(password).equalsIgnoreCase(userFound.getPassword())) {
             return userFound;
        }
return null;
        // System.out.println("userfound: " + userFound.getFirstName());
       

    } catch (Exception e) {
        throw new AuthException("Invalid email or password");
    }
}


}
