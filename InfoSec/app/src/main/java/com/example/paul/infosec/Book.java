package com.example.paul.infosec;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by paul on 11/12/14.
 */
public class Book {
    private UUID bId;
    private String bTitle;
    private String username;
    private String password;




    public Book() {
        //Generate Unique Identifier
        bId = UUID.randomUUID();



        /*
        for (int i = 0; i < 10; i++) {
            Page c = new Page();
            c.setTitle("Page" + i);
            Pages.add(c);
        }
        */
    }

    /*
    public ArrayList<Page> getPages() {
        return Pages;
    }
    */

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return bId;
    }

    public String getTitle() {
        return bTitle;
    }

    public void setId(UUID bId) {
        this.bId = bId;
    }

    public void setTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    @Override
    public String toString() {
        return bTitle;
    }
}
