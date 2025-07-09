package org.example.auth_backend;

import org.example.auth_backend.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class Db {
    public static List<Users> users = new ArrayList<>();

    public static void generateUsers(){
        users.addAll( List.of(
                new Users(1,"Tom","Hill",20,Status.SINGLE,"tom@gmail","1111"),
                new Users(2,"Jerry","May",21,Status.INRELATIONSHIP,"jerry@gmail","2222"),
                new Users(3,"Ben","Jones",22,Status.MARRIED,"ben@gmail","3333"),
                new Users(4,"Lily","Smith",19,Status.ENGAGED,"lily@gmail","4444")
        ));
    }

}
