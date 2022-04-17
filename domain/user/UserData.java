package domain.user;

public class UserData {
    private Long sequence;
    private String id;
    private String pw;

    public UserData() {
    }

    public UserData(Long sequence, String id, String pw) {
        this.sequence = sequence;
        this.id = id;
        this.pw = pw;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
