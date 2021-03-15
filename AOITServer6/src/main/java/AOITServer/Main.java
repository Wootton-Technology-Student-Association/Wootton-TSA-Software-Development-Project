package AOITServer;

import AOITServer.Adapters.ConcreteEmailClient;
import AOITServer.Adapters.ConcreteHttpRequestClient;
import AOITServer.Controllers.DailyEventsController;
import AOITServer.Controllers.MapController;
import AOITServer.Controllers.PasswordController;
import AOITServer.Controllers.UserController;
import AOITServer.Factories.JWTConcreteFactory;
import AOITServer.Observers.AccessManagerJWT;
import AOITServer.Observers.ServerLogging;
import AOITServer.Singletons.DatabaseSingleton;
import io.javalin.Javalin;
import javalinjwt.JavalinJWT;

import java.util.Map;

import static io.javalin.core.security.SecurityUtil.roles;


public class Main {
    public static void main(String[] args){


        String databaseUsername = "woottontsa@wootton-tsa-mysql-server";
        //please no hack
        String databasePassword = "Woot@2020";
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String databaseURL = "jdbc:mysql://wootton-tsa-mysql-server.mysql.database.azure.com:3306/AOITDatabase?serverTimezone=UTC&autoReconnect=true";
        String email = "dev.woottontsa.org@gmail.com";
        String emailPassword = "dev.woottontsa.org@Wootton2020";
        String googleMapsApiKey = "AIzaSyB0kTB0O417Co-wx3mm5lLIU3AdVuCICtc.";

        DatabaseSingleton ds = DatabaseSingleton.getInstance(jdbcDriver,databaseURL);
        ds.createConnection(databaseUsername,databasePassword);

        Javalin server = Javalin.create();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            ds.close();
            server.stop();

        }));

        JWTConcreteFactory jwtFactory = new JWTConcreteFactory("AAABBBCCC");
        server.before(JavalinJWT.createHeaderDecodeHandler(jwtFactory.getProvider()));

        AccessManagerJWT accessManager =new AccessManagerJWT("Username", "Role",
                Map.of("0",Roles.DEFAULT,"1",Roles.USER,"2",Roles.ADMIN), Roles.DEFAULT);
        server.config.accessManager(accessManager);


        UserController userController = new UserController(0,ds);
        server.post("/createUser",userController.createUser(),roles(Roles.DEFAULT,Roles.ADMIN));
        server.get("/validateToken",userController.validateToken(),roles(Roles.USER,Roles.ADMIN));
        server.post("/login",userController.loginUser(jwtFactory),roles(Roles.DEFAULT));
        server.post("/updateUserInfo",userController.updateUserInfo(),roles(Roles.DEFAULT));

        PasswordController passwordController = new PasswordController(0,ds);

        ConcreteEmailClient emailClient = new ConcreteEmailClient(email,emailPassword);
        server.post("/sendVerification",passwordController.sendValidationKey(emailClient),roles(Roles.DEFAULT));
        server.post("/resetPassword",passwordController.changePassword(),roles(Roles.DEFAULT));

        DailyEventsController dailyEvents = new DailyEventsController();
        server.post("/setCareerEvents",dailyEvents.setCareerEvents(),roles(Roles.ADMIN));
        server.get("/getCareerEvents",dailyEvents.getCareerEvents(),roles(Roles.USER,Roles.ADMIN));

        ConcreteHttpRequestClient rc = new ConcreteHttpRequestClient();
        MapController mapController = new MapController(googleMapsApiKey,rc);
        server.get("/getBanks",mapController.getBanks(),roles(Roles.USER,Roles.ADMIN));
        server.get("/getShelters",mapController.getShelter(),roles(Roles.USER,Roles.ADMIN));
        server.get("/getFoodBanks",mapController.getFoodBanks(),roles(Roles.USER,Roles.ADMIN));
        server.get("/getThriftStores",mapController.getThriftStores(),roles(Roles.USER,Roles.ADMIN));
        server.get("/getPublicServices",mapController.getPublicServices(),roles(Roles.USER,Roles.ADMIN));


         ServerLogging serverLog = new ServerLogging(0,ds);

        accessManager.addLoggingObserver(serverLog);

        int port = 8001;
        server.start(port);


    }
}
