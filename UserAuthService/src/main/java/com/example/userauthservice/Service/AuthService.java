package com.example.userauthservice.Service;

import com.example.userauthservice.DTOs.UserResponse;
import com.example.userauthservice.Exception.PasswordMissMatch;
import com.example.userauthservice.Exception.UserAlreadyExist;
import com.example.userauthservice.Exception.UserNotFoundException;
import com.example.userauthservice.Models.Role;
import com.example.userauthservice.Models.Session;
import com.example.userauthservice.Models.SessionStatus;
import com.example.userauthservice.Models.User;
import com.example.userauthservice.Repositories.SessionRepository;
import com.example.userauthservice.Repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    private final SecretKey key = Jwts.SIG.HS256.key().build();


    public ResponseEntity<?> signup(String name, String email, String password){

        if(userRepository.existsUserByEmail(email)){
            throw new UserAlreadyExist("email is already registered");
        }
        Role role=new Role();
        role.setRole("USER");
        List<Role> list=new ArrayList<>();
        User user=User.builder()
                .fullName(name)
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .roles(list)
                .build();
        try{
            User user1=userRepository.save(user);
            return new ResponseEntity<>(UserResponse.getInstance(user1),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public ResponseEntity<?> login(String email,String password){
        Optional<User> userOptional=userRepository.findByEmail(email);
        if(userOptional.isEmpty())
            throw new UserNotFoundException("No User exist with with email");
        User user=userOptional.get();
        if(!bCryptPasswordEncoder.matches(password,user.getPassword()))
            throw new PasswordMissMatch("incorrect password");

        LocalDateTime date=LocalDateTime.now().plusDays(5);
//        String token= RandomStringUtils.randomAlphabetic(20);
        Map<String,String> claim=new HashMap<>();
        claim.put("SessionStatus",SessionStatus.ACTIVE.toString());
        claim.put("expiredAt",date.toString());
        claim.put("issuedAt",LocalDateTime.now().toString());

        String token=Jwts.builder()             // to know how to build this check GitHub link
                .header().add("Userid",user.getId())
                .and()              // Link -> https://github.com/jwtk/jjwt?tab=readme-ov-file#quickstart
                .claims(claim)
                .signWith(key)
                .compact();

        Session session=Session.builder()
                .user(user)
                .token(token)
                .sessionStatus(SessionStatus.ACTIVE)
                .expiredAt(date)
                .build();
        sessionRepository.save(session);

        MultiValueMapAdapter<String,String> header=new MultiValueMapAdapter<>(new HashMap<>());
        header.put("auth_Token",List.of(token));

        UserResponse userResponse=UserResponse.getInstance(user);
        return new ResponseEntity<>(
                userResponse,
                header,
                HttpStatus.OK
        );
    }

    public boolean logOff(String token,long id){
        Optional<Session> optional=sessionRepository.findByToken(token);
        if(optional.isEmpty())return false;
        Session session=optional.get();
        if(session.getUser().getId()!=id) return false;
        session.setSessionStatus(SessionStatus.LOGGED_OUT);
        session.setExpiredAt(LocalDateTime.now());
        session.setDeleted(true);
        sessionRepository.save(session);
        return true;
    }

    public boolean validate(String token,long id){
        Optional<Session> optional=sessionRepository.findByToken(token);
        if(optional.isEmpty())return false;
        Session session=optional.get();
        if(session.isDeleted())return false;
        if(session.getSessionStatus()!=SessionStatus.ACTIVE)return false;
        if(session.getUser().getId()!=id)return false;
        LocalDateTime expiry=session.getExpiredAt();
        LocalDateTime now=LocalDateTime.now();
        if(!expiry.isAfter(now))return false;
        return true;
    }

}
