package com.maxcheung.funddata.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.maxcheung.funddata.domain.UserData;
import com.maxcheung.funddata.domain.UsersDTO;
import com.maxcheung.funddata.service.UserService;


 
@RestController
@RequestMapping(value = "/v6/funddata")
public class FundDataController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Users--------------------------------------------------------
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserData>> listAllUsers() {
        List<UserData> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<UserData>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserData>>(users, HttpStatus.OK);
    }
 
    //-------------------Retrieve All Users--------------------------------------------------------
    @RequestMapping(value = "/userDTO/", method = RequestMethod.GET)
    public ResponseEntity<UsersDTO> listAllUsersDTO() {
    	UsersDTO usersDTO = new UsersDTO();
        List<UserData> users = userService.findAllUsers();
        usersDTO.setUsers(users);
        if(users.isEmpty()){
            return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.OK);
    }
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserData> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        UserData user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserData>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserData>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserData user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName());
 
        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserData> updateUser(@PathVariable("id") long id, @RequestBody UserData user) {
        System.out.println("Updating User " + id);
         
        UserData currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserData>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
         
        userService.updateUser(currentUser);
        return new ResponseEntity<UserData>(currentUser, HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserData> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        UserData user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<UserData>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<UserData>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<UserData> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<UserData>(HttpStatus.NO_CONTENT);
    }
 
}
