package service.user;

import config.Config;
import domain.user.UserData;
import domain.user.UserJoinData;
import repository.UserList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDelImpl implements UserDel {
    private UserData userData = new UserData();
    private Scanner sc = new Scanner(System.in);
    private ArrayList<UserData> userArray;

    @Override
    public void userDel() {
        System.out.print("회원 키를 입력 해주세요: ");
        Long index = sc.nextLong();
        UserList userList = new UserList();

        userArray = userList.getUserList();

        boolean flag = true;

        for (UserData data : userArray) {
            if (data.getIndex() == index) {
                System.out.print("[Info] " + data.getId() + " 이거 맞나요? (y/n)");

                if (isTrue(sc.next())) {
                    flag = false;
                    delete(data);
                    break;
                } else {
                    flag = true;
                    continue;
                }
            }
        }

        if(flag){
            System.out.println("[Info] 일치하는 계정이 없습니다.");
        }


    }

    private void delete(UserData data) {
        //TODO:유저 삭제 로직
        // 1. 전수검사
        // 2. index 일치하는 계정 삭제된 array 생성
        // 3. 생성된 array 토대로 새 파일 생성

        ArrayList<UserData> newUserArray = new ArrayList<>();

        for (UserData userData : userArray) {
            
            if (data.getIndex().equals(userData.getIndex())){
                continue;
            }
            newUserArray.add(userData);
        }

        saveUserData(newUserArray);

    }

    private boolean isTrue(String answer) {
        return answer.equals("y") || answer.equals("Y") || answer.equals("1");
    }

    private boolean saveUserData(ArrayList<UserData> newUserArray) {
        boolean isSaved = true;
        String repo = Config.USER_REPO;

        try {
            OutputStream out = new FileOutputStream(repo);
            OutputStreamWriter outW = new OutputStreamWriter(out);
            PrintWriter pw = new PrintWriter(outW);

            StringBuilder sb = new StringBuilder();

            for (UserData data : newUserArray) {
                sb.append(data.getIndex()).append("/");
                sb.append(data.getId()).append("/");
                sb.append(data.getPw()).append(System.lineSeparator());
            }

            pw.print(sb.toString());
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return isSaved;
    }

}
