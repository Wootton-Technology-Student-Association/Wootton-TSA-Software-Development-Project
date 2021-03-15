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

        String sqlSet = String.format("INSERT INTO %s (?) VALUES (?);", AOITUsersTable.TABLENAME);
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
            passwordSet = ds.getPreparedStatement(index7);
            passwordSet.setString(1, AOITUsersTable.PASSWORD);

            int index8 = ds.createPreparedStatement(connectionIndex, sqlGet);
            phoneSet = ds.getPreparedStatement(index8);
            phoneSet.setString(1, AOITUsersTable.PHONENUMBER);

            int index9 = ds.createPreparedStatement(connectionIndex, sqlGet);
            emailSet = ds.getPreparedStatement(index9);
            emailSet.setString(1, AOITUsersTable.EMAIL);

            int index10 = ds.createPreparedStatement(connectionIndex, sqlGet);
            birthSet = ds.getPreparedStatement(index10);
            birthSet.setString(1, AOITUsersTable.BIRTHDAY);

            int index11 = ds.createPreparedStatement(connectionIndex, sqlGet);
            addressSet = ds.getPreparedStatement(index11);
            addressSet.setString(1, AOITUsersTable.ADDRESS);

            int index12 = ds.createPreparedStatement(connectionIndex, sqlGet);
            nameSet = ds.getPreparedStatement(index12);
            nameSet.setString(1, AOITUsersTable.NAME);




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

    public String updateUsername(UsernameSubject a) {
       return a.getUsername();
    }


}
