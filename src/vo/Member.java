package vo;

public class Member {

    int member_idx;
    String id;
    String pw;

    public Member(int member_idx, String id, String pw) {
        this.member_idx = member_idx;
        this.id = id;
        this.pw = pw;
    }

    public int getMember_idx() {
        return member_idx;
    }

    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getLeave_date() {
        return leave_date;
    }

    public void setLeave_date(String leave_date) {
        this.leave_date = leave_date;
    }

    public String getMenber_state() {
        return menber_state;
    }

    public void setMenber_state(String menber_state) {
        this.menber_state = menber_state;
    }

    String nickname;
    String name;
    String phone_number;
    String join_date;
    String leave_date;
    String menber_state;

}

//    member_idx NUMBER,
//    id VARCHAR2(200) NULL,
//    pw VARCHAR2(200) NULL,
//    nickname VARCHAR2(50) NULL,
//    name VARCHAR2(50) NULL,
//    phone_number VARCHAR2(200) NULL,
//    join_date DATE NULL,
//        leave_date DATE NULL,
//        menber_state NUMBER NULL,
//        PRIMARY KEY (member_idx));