package AOITServer.Controllers;

import AOITServer.JsonClasses.MessageJson;
import AOITServer.Observers.UsernameObserver;
import AOITServer.Observers.UsernameSubject;
import AOITServer.Singletons.DatabaseSingleton;
import AOITServer.Tables.AOITUsersTable;
import io.javalin.http.Handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InformationController implements UsernameObserver {
   private UsernameSubject usernameSubject;

   private PreparedStatement passwordSet;
   private PreparedStatement passwordGet;
    private PreparedStatement phoneSet;
    private PreparedStatement phoneGet;
    private PreparedStatement emailSet;
    private PreparedStatement emailGet;
    private PreparedStatement birthGet;
    private PreparedStatement birthSet;
    private PreparedStatement addressGet;
    private PreparedStatement addressSet;
    private PreparedStatement nameGet;
    private PreparedStatement nameSet;



    public InformationController(int connectionIndex, DatabaseSingleton ds,UsernameSubject subject){
        usernameSubject = subject;

        String sqlSet = String.format("UPDATE %s SET ?=? WHERE %s=?", AOITUsersTable.TABLENAME,AOITUsersTable.USERNAME);
        String sqlGet = String.format("SELECT ? FROM %s",AOITUsersTable.TABLENAME);

        try {

            int index1 = ds.createPreparedStatement(connectionIndex, sqlSet);
            passwordSet = ds.getPreparedStatement(index1);
            passwordSet.setString(1, AOITUsersTable.PASSWORD);


            int index2 = ds.createPreparedStatement(connectionIndex, sqlSet);
            phoneSet = ds.getPreparedStatement(index2);
            phoneSet.setString(1, AOITUsersTable.PHONENUMBER);


            int index3 = ds.createPreparedStatement(connectionIndex, sqlSet);
            emailSet = ds.getPreparedStatement(index3);
            emailSet.setString(1, AOITUsersTable.EMAIL);

            int index4 = ds.createPreparedStatement(connectionIndex, sqlSet);
            birthSet = ds.getPreparedStatement(index4);
            birthSet.setString(1, AOITUsersTable.BIRTHDAY);

            int index5 = ds.createPreparedStatement(connectionIndex, sqlSet);
            addressSet = ds.getPreparedStatement(index5);
            addressSet.setString(1, AOITUsersTable.ADDRESS);

            int index6 = ds.createPreparedStatement(connectionIndex, sqlSet);
            nameSet = ds.getPreparedStatement(index6);
            nameSet.setString(1, AOITUsersTable.NAME);

            int index7 = ds.createPreparedStatement(connectionIndex, sqlGet);
            passwordGet = ds.getPreparedStatement(index7);
            passwordGet.setString(1, AOITUsersTable.PASSWORD);

            int index8 = ds.createPreparedStatement(connectionIndex, sqlGet);
            phoneGet = ds.getPreparedStatement(index8);
            phoneGet.setString(1, AOITUsersTable.PHONENUMBER);

            int index9 = ds.createPreparedStatement(connectionIndex, sqlGet);
            emailGet = ds.getPreparedStatement(index9);
            emailGet.setString(1, AOITUsersTable.EMAIL);

            int index10 = ds.createPreparedStatement(connectionIndex, sqlGet);
            birthGet = ds.getPreparedStatement(index10);
            birthGet.setString(1, AOITUsersTable.BIRTHDAY);

            int index11 = ds.createPreparedStatement(connectionIndex, sqlGet);
            addressGet = ds.getPreparedStatement(index11);
            addressGet.setString(1, AOITUsersTable.ADDRESS);

            int index12 = ds.createPreparedStatement(connectionIndex, sqlGet);
            nameGet = ds.getPreparedStatement(index12);
            nameGet.setString(1, AOITUsersTable.NAME);




        }catch(SQLException s){
            System.out.println(s);
        }


    }

    public Handler getName(){
        return ctx ->{
            try {
                nameGet.setString(2,updateUsername(usernameSubject));
                ResultSet rs =  nameGet.executeQuery();

                if(rs.next()){
                    ctx.json(new MessageJson(true, rs.getString(AOITUsersTable.NAME)));

                }
                else{
                    ctx.json(new MessageJson(false,"Name not found"));
                }

                rs.close();
            }catch(SQLException s){
                ctx.json(new MessageJson(false,"Could not get Name"));
            }
        };
    }

    public Handler getPassword(){
        return ctx ->{
            try {
                passwordGet.setString(2,updateUsername(usernameSubject));
                ResultSet rs =  passwordGet.executeQuery();

                if(rs.next()){
                    ctx.json(new MessageJson(true, rs.getString(AOITUsersTable.PASSWORD)));

                }
                else{
                    ctx.json(new MessageJson(false,"Password not found"));
                }

                rs.close();
            }catch(SQLException s){
                ctx.json(new MessageJson(false,"Could not get Password"));
            }
        };
    }

    public Handler getEmail(){
        return ctx ->{
            try {
                emailGet.setString(2,updateUsername(usernameSubject));
                ResultSet rs =  emailGet.executeQuery();

                if(rs.next()){
                    ctx.json(new MessageJson(true, rs.getString(AOITUsersTable.EMAIL)));

                }
                else{
                    ctx.json(new MessageJson(false,"Email not found"));
                }

                rs.close();
            }catch(SQLException s){
                ctx.json(new MessageJson(false,"Could not get Email"));
            }
        };
    }

    public Handler getAddress(){
        return ctx ->{
            try {
                addressGet.setString(2,updateUsername(usernameSubject));
                ResultSet rs =  addressGet.executeQuery();

                if(rs.next()){
                    ctx.json(new MessageJson(true, rs.getString(AOITUsersTable.ADDRESS)));

                }
                else{
                    ctx.json(new MessageJson(false,"Address not found"));
                }

                rs.close();
            }catch(SQLException s){
                ctx.json(new MessageJson(false,"Could not get Address"));
            }
        };
    }

    public Handler getPhone(){
        return ctx ->{
            try {
                phoneGet.setString(2,updateUsername(usernameSubject));
                ResultSet rs =  nameGet.executeQuery();

                if(rs.next()){
                    ctx.json(new MessageJson(true, rs.getString(AOITUsersTable.PHONENUMBER)));

                }
                else{
                    ctx.json(new MessageJson(false,"Phone Number not found"));
                }

                rs.close();
            }catch(SQLException s){
                ctx.json(new MessageJson(false,"Could not get Phone Number"));
            }
        };
    }

    public Handler getBirthday(){
        return ctx ->{
            try {
                birthGet.setString(2,updateUsername(usernameSubject));
                ResultSet rs =  birthGet.executeQuery();

                if(rs.next()){
                    ctx.json(new MessageJson(true, rs.getString(AOITUsersTable.PHONENUMBER)));

                }
                else{
                    ctx.json(new MessageJson(false,"Birthday not found"));
                }

                rs.close();
            }catch(SQLException s){
                ctx.json(new MessageJson(false,"Could not get Birthday"));
            }
        };
    }

    public Handler setName(){
        return ctx ->{
            String name = ctx.queryParam("Name");

            if(name != null) {

                try {
                    nameSet.setString(2,name);
                    nameSet.setString(3,updateUsername(usernameSubject));
                    nameSet.execute();

                    ctx.json(new MessageJson(true, "Name set"));
                } catch (SQLException s) {
                    System.out.println(s);
                    ctx.json(new MessageJson(false, "Couldnt set name"));
                }
            }
            else{
                ctx.json(new MessageJson(false,"Query param \"Name\" not found"));
            }
        };
    }

    public Handler setPassword(){
        return ctx ->{
            String password = ctx.queryParam("Password");

            if(password != null) {

                try {
                    passwordSet.setString(2,password);
                    passwordSet.setString(3,updateUsername(usernameSubject));
                    passwordSet.execute();

                    ctx.json(new MessageJson(true, "Password set"));
                } catch (SQLException s) {
                    System.out.println(s);
                    ctx.json(new MessageJson(false, "Couldnt set password"));
                }
            }
            else{
                ctx.json(new MessageJson(false,"Query param \"Password\" not found"));
            }
        };
    }

    public Handler setBirthday(){
        return ctx ->{
            String birthday = ctx.queryParam("Birthday");

            if(birthday != null) {

                try {
                    birthSet.setString(2,birthday);
                    birthSet.setString(3,updateUsername(usernameSubject));
                    birthSet.execute();

                    ctx.json(new MessageJson(true, "Birthday set"));
                } catch (SQLException s) {
                    System.out.println(s);
                    ctx.json(new MessageJson(false, "Couldnt set Birthday"));
                }
            }
            else{
                ctx.json(new MessageJson(false,"Query param \"Birthday\" not found"));
            }
        };
    }

    public Handler setEmail(){
        return ctx ->{
            String email = ctx.queryParam("Email");

            if(email != null) {

                try {
                    emailSet.setString(2,email);
                    emailSet.setString(3,updateUsername(usernameSubject));
                    emailSet.execute();

                    ctx.json(new MessageJson(true, "Email set"));
                } catch (SQLException s) {
                    System.out.println(s);
                    ctx.json(new MessageJson(false, "Couldnt set email"));
                }
            }
            else{
                ctx.json(new MessageJson(false,"Query param \"Email\" not found"));
            }
        };
    }

    public Handler setAddress(){
        return ctx ->{
            String address = ctx.queryParam("Address");

            if(address != null) {

                try {
                    addressSet.setString(2,address);
                    addressSet.setString(3,updateUsername(usernameSubject));
                    addressSet.execute();

                    ctx.json(new MessageJson(true, "Address set"));
                } catch (SQLException s) {
                    System.out.println(s);
                    ctx.json(new MessageJson(false, "Couldnt set address"));
                }
            }
            else{
                ctx.json(new MessageJson(false,"Query param \"Address\" not found"));
            }
        };
    }

    public Handler setPhoneNumber(){
        return ctx ->{
            String phoneNumber = ctx.queryParam("Phone");

            if(phoneNumber != null) {

                try {
                    phoneSet.setString(2,phoneNumber);
                    phoneSet.setString(3,updateUsername(usernameSubject));
                    phoneSet.execute();

                    ctx.json(new MessageJson(true, "Phone Number set"));
                } catch (SQLException s) {
                    System.out.println(s);
                    ctx.json(new MessageJson(false, "Couldnt set phone number"));
                }
            }
            else{
                ctx.json(new MessageJson(false,"Query param \"Phone Number\" not found"));
            }
        };
    }

    public String updateUsername(UsernameSubject a) {
       return a.getUsername();
    }


}
