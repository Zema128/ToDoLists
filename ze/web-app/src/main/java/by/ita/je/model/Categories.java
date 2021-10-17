package by.ita.je.model.enams;

public enum Categories {
    PERSONAL("P"), WORK("W"), USERS("U");

    private String code;

    private Categories(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
