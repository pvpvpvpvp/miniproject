package service.user;

import config.Config;
import domain.user.UserData;
import domain.user.UserJoinData;
import repository.UserList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserJoinImpl implements UserJoin {
    private PwRegExp regExp = Config.getPwRegExpInstance();
    private UserJoinData userJoinData = new UserJoinData();

    private Scanner sc = new Scanner(System.in);

    @Override
    public void userJoin() {
        boolean flag = true;

        while (flag) {
            System.out.print("아이디를 입력해 주세요: ");
            userJoinData.setId(sc.next());

            if (userIdIsDuplicate(userJoinData)) {
                System.out.println("[Info] 이미 등록된 계정입니다.");
                continue;
            }

            System.out.print("비밀번호를 입력해 주세요: ");
            userJoinData.setPw(sc.next());

            if (isUserPwIsRight(userJoinData)) {
                System.out.println("[Info] 비밀번호 규칙에 맞게 설정해 주세요.");
                continue;
            }

            if(!addUserData(userJoinData)){
                System.out.println("[Info] 가입에 실패 했습니다. 다시 시도 해주세요.");
                continue;
            } else {
                System.out.println("[Info] 가입되었습니다.");
            }

            flag = false;
        }
    }

    private boolean addUserData(UserJoinData userJoinData) {
        boolean isSaved = true;
        String repo = Config.USER_REPO;

        try {
            Reader in = new FileReader(repo);
            BufferedReader bIn = new BufferedReader(in);

            OutputStream out = new FileOutputStream(repo,true);
            OutputStreamWriter outW = new OutputStreamWriter(out);
            PrintWriter pw = new PrintWriter(outW);

            StringBuilder sb = new StringBuilder();

            while (true) {
                String line = bIn.readLine();

                if (line == null) {
                    break;
                }

            }

            sb.append(new UserIndex().indexUp()).append("/");
            sb.append(userJoinData.getId()).append("/");
            sb.append(userJoinData.getPw());

            System.out.println(sb.toString());

            pw.println(sb.toString());
            pw.flush();

            bIn.close();
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return isSaved;
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
//            System.out.println(userJoinData.getId().equals(userData.getId())+" "+userJoinData.getId()+" "+userData.getId());
            if (userJoinData.getId().equals(userData.getId())) {

                res = true;
            }
        }

        return res;
    }

}
