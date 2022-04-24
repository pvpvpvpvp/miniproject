package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String id;
    private String pw0;
    private String pw1; // 나중에 빼기
    private int adminIdx;
    private String nickname;
    private int grade;

    public String getId() {
        return id;
    }

    public String getPw0() {
        return pw0;
    }

    public String getPw1() {
        return pw1;
    }

    public int getAdminIdx() {
        return adminIdx;
    }

    public String getNickname() {
        return nickname;
    }

    public int getGrade() {
        return grade;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw0(String pw0) {
        this.pw0 = pw0;
    }

    public void setPw1(String pw1) {
        this.pw1 = pw1;
    }

    public void setAdminIdx(int adminIdx) {
        this.adminIdx = adminIdx;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

