package fr._42.chat.repositories;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr._42.chat.exceptions.UserNotFoundException;
import fr._42.chat.models.Chatroom;

import javax.sql.DataSource;

import fr._42.chat.models.User;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


public class UserRepositoryJdbcImpl implements UserRepository {
    private final DataSource dataSource;
    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String loadSqlQuery(String fileName) throws URISyntaxException {
        // Get the URL of the resource
        URL resourceUrl = getClass().getClassLoader().getResource(fileName);
        if (resourceUrl == null) {
            throw new RuntimeException("Resource not found: " + fileName);
        }

        // Print the absolute path
//        System.out.println("Absolute path of the resource: " + new File(resourceUrl.toURI()).getAbsolutePath());

        // Now load the content of the file
        try (InputStream is = resourceUrl.openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<User> getUserById(int id)
    {
        String query = "SELECT * FROM \"User\" WHERE id = ?";
        try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet st = pst.executeQuery();
            if (!st.next())
                throw new UserNotFoundException("User with id : " + id+ " Not Found");
//            st.next();
            User user = new User(st.getInt("id"), st.getString("login"), st.getString("password"));
            return Optional.of(user);

        } catch (SQLException e) {
            System.err.println("test = "+ e.getMessage());
            throw new UserNotFoundException(e.getMessage());

        }
    }

    @Override
    public List<User> findAll(int page, int size)
    {
        ArrayList<User> list = new ArrayList<>();
        try {
            PreparedStatement pst = getResultSet(page, size);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User newUser = new User(rs.getInt("userid"), rs.getString("login"), rs.getString("password"));
                ArrayList<Chatroom> crA = new ArrayList<>();
                ArrayList<Chatroom> srA = new ArrayList<>();

                Integer[] so_ids = (Integer[])rs.getArray("so_ids").getArray();
                String[] so_names = (String[])rs.getArray("so_names").getArray();
                Object[] so_owners = (Object[])rs.getArray("so_owners").getArray();

                Integer[] cr_ids = (Integer[])rs.getArray("cr_ids").getArray();
                String[] cr_names = (String[])rs.getArray("cr_names").getArray();
                Object[] cr_owners = (Object[])rs.getArray("cr_owners").getArray();

                fillRoom(crA,  cr_owners, cr_ids, cr_names);
                fillRoom(srA, so_owners, so_ids, so_names);
                newUser.setCreatedRooms(crA);
                newUser.setSocializingRooms(srA);
                list.add(newUser);
//                System.out.println("userid =  " + rs.getInt("userid") + " cr users " + rs.getArray("cr_owners")  +   " cr chatRooms = " + rs.getArray("cr_names") + " cr ids :" + rs.getArray("cr_ids") + " so users " + rs.getArray("so_owners")  + " so ids = " + rs.getArray("so_ids") + " so_names" + rs.getArray("so_names"));

            }
        } catch (Exception e)
        {
            System.err.println("Error : " +e.getMessage());
        }
        return list;
    }

    private PreparedStatement getResultSet(int page, int size) throws SQLException, URISyntaxException {
        String query = loadSqlQuery("findAllUser.sql");
//        System.out.println("query = " + query);
        Connection con = dataSource.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, page);
        st.setInt(2, size);
        return st;
//        try (ResultSet rs = st.executeQuery()) {
//            return rs;
//        }
    }

    private void fillRoom(ArrayList<Chatroom> srA, Object[] so_owners, Integer[] cr_ids, String[] cr_names) {
        for (int i = 0; i < cr_ids.length; i++ ) {
            String userInfo = (String) so_owners[i];

//            System.out.println(cr_ids[i]);
//            System.out.println(userInfo);
//            System.out.println(cr_names[i]);
            ObjectMapper objectMapper = new ObjectMapper();
//            Map<String, Object> userMap;
            try {
               var userMap = objectMapper.readValue(userInfo, Map.class);

                // Access fields from the Map
                int id = (int) userMap.get("id");
                String login = (String) userMap.get("login");
                String password = (String) userMap.get("password");
                User user = new User(id, login, password);
                srA.add(new Chatroom(cr_ids[i], user, cr_names[i]));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
//            System.out.println(user.getId() +  " " + user.getLogin());
//            srA.add(new Chatroom(cr_ids[i], new User(userId, parts[1], parts[2]), cr_names[i]));
//            srA.add(new Chatroom(cr_ids[i], user, cr_names[i]));

        }
    }

}