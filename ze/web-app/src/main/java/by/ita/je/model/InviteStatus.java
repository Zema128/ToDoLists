package by.ita.je.model;

public enum InviteStatus {
        ACCEPTED("A"), PROCESSING("P"), DENIED("D");

private String code;

private InviteStatus(String code){
        this.code = code;
        }

public String getCode(){
        return code;
        }
}