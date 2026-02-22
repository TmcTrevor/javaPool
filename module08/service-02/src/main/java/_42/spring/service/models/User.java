package _42.spring.service.models;

public class User {
    private final long identifier;
    private  String email;
    private  static long NEXT_ID;
    private String password;

    public User()
    {
        NEXT_ID++;
        identifier = NEXT_ID;
        email = "random"  + identifier + "@gmail.com";
    }

    public User(String email)
    {
        NEXT_ID++;
        identifier = NEXT_ID;
        this.email = email;
    }


    public User(String email, String password)
    {
        NEXT_ID++;
        identifier = NEXT_ID;
        this.email = email;
        this.password = password;
    }


    public User(long id, String email, String password) {
        identifier = id;
        this.email = email;
        this.password = password;
        if (NEXT_ID <= id)
            NEXT_ID = id + 1;
    }

    public User(long id, String email) {
        identifier = id;
        this.email = email;
        if (NEXT_ID <= id)
            NEXT_ID = id + 1;
    }

    public long getIdentifier() {
        return identifier;
    }
    public String getEmail() {
        return  email;
    }
    public  String getPassword()
    {
        return  this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    @Override
    public  String toString()
    {
        return "{ ID : "  + identifier +  ", Email : " + email + ", password : " + password +  " }";
    }
}
