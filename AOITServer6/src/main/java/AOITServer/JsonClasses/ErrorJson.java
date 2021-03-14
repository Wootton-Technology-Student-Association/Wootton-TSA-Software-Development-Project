package AOITServer.JsonClasses;

public class ErrorJson {
    public ErrorJson(boolean success,String error){
        Success = success;
        Error = error;
    }

    public ErrorJson(){}

    public boolean Success;
    public String Error;
}
