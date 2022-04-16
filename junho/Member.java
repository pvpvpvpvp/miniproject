package junho;

public class Member {
    private String Id;
    private String pass1;
    private String pass2;

    public  Member(){}
    public Member(String id, String pass1, String pass2) {
        super();
        Id = id;
        this.pass1 = pass1;
        this.pass2 = pass2;
    }
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getPass1() {
        return pass1;
    }
    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }
    public String getPass2() {
        return pass2;
    }
    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }


}
