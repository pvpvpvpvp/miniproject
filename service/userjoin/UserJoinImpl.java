package service.userjoin;

import com.sun.tools.javac.Main;
import config.Config;
import domain.user.UserData;
import domain.user.UserJoinData;
import service.userList.UserList;

import java.util.ArrayList;
import java.util.Scanner;

public class UserJoinImpl implements UserJoin {
    private PwRegExp regExp = Config.getPwRegExpInstance();
    private UserJoinData userJoinData = new UserJoinData();

    private Scanner sc = new Scanner(System.in);

    public UserJoinImpl() {
    }

    @Override
    public void join() {
        boolean flag = true;

        while (flag) {
            System.out.print("아이디를 입력해 주세요: ");
            userJoinData.setId(sc.next());

            if (userIdIsDuplicate(userJoinData)) {
                System.out.println("이미 등록된 계정입니다.");
                continue;
            }

            System.out.print("비밀번호를 입력해 주세요: ");
            userJoinData.setPw(sc.next());

            if (isUserPwIsRight(userJoinData)) {
                System.out.println("비밀번호 규칙에 맞게 설정해 주세요.");
                continue;
            }

            flag = false;
        }

    }

    private boolean isUserPwIsRight(UserJoinData userJoinData) {
        return regExp.pwRegExp(userJoinData);
    }

    @Override
    public boolean userIdIsDuplicate(UserJoinData userJoinData) {
        UserList userList = new UserList();
        ArrayList<UserData> userDataList = userList.getUserList();

        boolean res = false;

        for (UserData userData : userDataList) {
            if (userJoinData.getId().equals(userData.getId())) {
                res = true;
            }
        }

        return res;
    }
}
