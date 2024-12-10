package com.project.Crud_Operation.exception;

public class UserNotFoundException extends  RuntimeException {

    public  UserNotFoundException(Long id){
        super("User not Found" + " " + id);
    }

}
