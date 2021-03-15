package AOITServer.Controllers;

import AOITServer.Factories.JWTFactory;
import AOITServer.JsonClasses.ErrorJson;
import AOITServer.JsonClasses.JWTJson;
import AOITServer.Singletons.DatabaseSingleton;
import AOITServer.Tables.AOITUsersTable;
import io.javalin.http.Handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserController {
    private PreparedStatement psCreateUser;
    private PreparedStatement psLogin;
    private PreparedStatement psUpdateUserInfo;

    public UserController(int connectionIndex,DatabaseSingleton ds){
        String sqlCreateUser = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                AOITUsersTable.TABLENAME, AOITUsersTable.USERNAME, AOITUsersTable.PASSWORD
                , AOITUsersTable.ROLE);

        String sqlLogin = String.format("SELECT %s FROM %s WHERE %s = ? AND %s = ?",AOITUsersTable.ROLE,
                AOITUsersTable.TABLENAME,AOITUsersTable.USERNAME,AOITUsersTable.PASSWORD);

        String sqlUpdateUserInfo = String.format(
                "UPDATE %s SET %s = ?,  %s = ?,  %s = ?,  %s = ?,  %s = ? WHERE %s = ?",
                AOITUsersTable.TABLENAME,
                AOITUsersTable.NAME,
                AOITUsersTable.ADDRESS,
                AOITUsersTable.BIRTHDAY,
                AOITUsersTable.EMAIL,
                AOITUsersTable.PHONENUMBER,
                AOITUsersTable.USERNAME);

        int index1 = ds.createPreparedStatement(connectionIndex,sqlCreateUser);
        int index2 = ds.createPreparedStatement(connectionIndex,sqlLogin);
        int index3 = ds.createPreparedStatement(connectionIndex,sqlUpdateUserInfo);

        psCreateUser = ds.getPreparedStatement(index1);
        psLogin = ds.getPreparedStatement(index2);
        psUpdateUserInfo = ds.getPreparedStatement(index3);

    }
    public Handler createUser(){
        return ctx ->{
            String username = ctx.queryParam("Username");
            String password = ctx.queryParam("Password");
            if(username == null){
                ctx.status(404).json(new ErrorJson(false,"Query parameter \"Username\" not found"));
            }
            else if(password == null){
                ctx.status(404).json(new ErrorJson(false,"Query parameter \"Password\" not found"));
            }
            else{
                psCreateUser.setString(1,username);
                psCreateUser.setString(2,password);
                psCreateUser.setInt(3,1);

                try {
                    psCreateUser.execute();
                }catch(SQLIntegrityConstraintViolationException e){
                    ctx.status(404).json(new ErrorJson(false,"Username already exists"));
                }catch(SQLException e){
                    System.out.println(e);
                    ctx.status(404).json(new ErrorJson(false,"Could not create User"));
                }
            }
        };
    }

    public Handler loginUser(JWTFactory jwtFactory){
        return ctx ->{

            String username = ctx.queryParam("Username");
            String password = ctx.queryParam("Password");
            if(username == null){
                ctx.status(404).json(new ErrorJson(false,"Query parameter \"Username\" not found"));
            }
            else if(password == null){
                ctx.status(404).json(new ErrorJson(false,"Query parameter \"Password\" not found"));
            }
            else{
                psLogin.setString(1,username);
                psLogin.setString(2,password);

                try {
                    ResultSet rs = psLogin.executeQuery();

                    if(rs.next()){
                        int role = rs.getInt(AOITUsersTable.ROLE);
                        ctx.json(new JWTJson(jwtFactory.createToken(username,role + "")));
                    }

                    else{
                        ctx.status(401).json(new ErrorJson(false,"Unauthorized"));
                    }

                    rs.close();

                }catch(SQLException e){
                    ctx.status(401).json(new ErrorJson(false,"Unauthorized"));
                }

            }
        };
    }

    public Handler validateToken(){
        return ctx ->{
          ctx.json(new ErrorJson(true,""));
        };
    }

    public Handler updateUserInfo(){
        return ctx ->{
            String username = ctx.queryParam("Username");
            String name = ctx.queryParam("Name");
            String address = ctx.queryParam("Address");
            String birthday = ctx.queryParam("Birthday");
            String email = ctx.queryParam("Email");
            String phonenumber = ctx.queryParam("Phone");

            if(username == null){
                ctx.status(404).json(new ErrorJson(false,"Query parameter \"Username\" not found"));
            }
            else if(name == null){
                ctx.status(404).json(new ErrorJson(false,"Query parameter \"Name\" not found"));
            }
            else{
                psUpdateUserInfo.setString(1,name);
                psUpdateUserInfo.setString(2,address);
                psUpdateUserInfo.setString(3,birthday);
                psUpdateUserInfo.setString(4,email);
                psUpdateUserInfo.setString(5,phonenumber);
                psUpdateUserInfo.setString(6,username);

                try {
                    psUpdateUserInfo.execute();
                }catch(SQLException e){
                    System.out.println(e);
                    ctx.status(404).json(new ErrorJson(false,"Could not update User Info"));
                }
            }
        };
    }
}
