package repository;

import config.Config;
import domain.user.UserData;

import java.io.*;
import java.util.ArrayList;

public class UserList {

    public ArrayList<UserData> getUserList() {

        ArrayList<UserData> userDataList = new ArrayList<>();

        try {
            Reader in = new FileReader(Config.USER_REPO);
            BufferedReader bIn = new BufferedReader(in);

            while(true){
                String line = bIn.readLine();

                if(line==null){
                    break;
                }

                String[] splitLine = line.split("/");

                UserData userData = new UserData();

                userData.setIndex(Long.parseLong(splitLine[0]));
                userData.setId(splitLine[1]);
                userData.setPw(splitLine[2]);

                userDataList.add(userData);
            }

            bIn.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userDataList;
    }

    public void showUserList() {
        try {
            Reader in = new FileReader(Config.USER_REPO);
            BufferedReader bIn = new BufferedReader(in);

            System.out.println();
            System.out.printf("%-3s | %-3s | %-3s \n", "Idx", "Id", "Pw");

            while(true){
                String line = bIn.readLine();

                if(line==null){
                    break;
                }

                String[] splitLine = line.split("/");

                System.out.printf("%-3s | %-3s | %-3s \n", splitLine[0], splitLine[1], splitLine[2]);
            }

            bIn.close();

            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
