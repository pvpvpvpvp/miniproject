package service.userList;

import domain.user.UserData;

import java.io.*;
import java.util.ArrayList;

public class UserList {
    private static final String USER_REPO = "userRepo.txt";

    public ArrayList<UserData> getUserList() {

        ArrayList<UserData> userDataList = new ArrayList<>();
        UserData userData = new UserData();

        try {
            Reader in = new FileReader(USER_REPO);
            BufferedReader bIn = new BufferedReader(in);

            while(true){
                String line = bIn.readLine();

                if(line==null){
                    break;
                }

                String[] splitLine = line.split("/");

                userData.setId(splitLine[1]);
                userData.setPw(splitLine[2]);

                userDataList.add(userData);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userDataList;
    }

    public void showUserList() {
        try {
            Reader in = new FileReader(USER_REPO);
            BufferedReader bIn = new BufferedReader(in);

            while(true){
                String line = bIn.readLine();
                if(line==null){
                    break;
                }

                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
