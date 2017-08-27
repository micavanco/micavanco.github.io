package files;

import java.io.*;

public class User
{
    private String name;
    private char[] password;

    public User(String u,char[] p)
    {
        name=u;
        password=p;
    }
    public User(){}

    public String getName() {
        return name;
    }

    public char[] getPassword() {
        return password;
    }

}
