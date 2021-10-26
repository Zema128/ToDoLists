package by.ita.je.model.enams;

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