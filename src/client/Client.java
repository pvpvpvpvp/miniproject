package client;

import banner.Banner;
import config.Config;
import repository.UserList;
import service.user.UserDel;
import service.user.UserDelImpl;
import service.user.UserJoin;

import java.util.Scanner;

public class Client{
    public static boolean flag = true;

    private boolean isStarted = false;
    private UserList userList = new UserList();
    private UserJoin userJoin = Config.getUserJoinInstance();
    private UserDel userDel = Config.getUserDelInstance();

    public void showMenu() {

        Scanner sc = new Scanner(System.in);
        showBanner();

        while (flag) {
            System.out.println("1. 회원 목록 조회");
            System.out.println("2. 회원 가입");
            System.out.println("3. 회원 삭제");
            System.out.println("4. 종료");

            System.out.print("메뉴를 선택해 주세요 : ");

            switch (sc.nextInt()) {
                case 1:
                    userList.showUserList();
                    break;
                case 2:
                    userJoin.userJoin();
                    break;
                case 3:
                    userDel.userDel();
                    break;
                case 4:
                    flag = false;
                    break;
            }
        }

        System.out.println("종료되었습니다.");
    }

    private void showBanner() {
        if (isStarted) {
            Banner banner = new Banner();
            banner.showBanner();
            isStarted = false;
        }
    }
}
