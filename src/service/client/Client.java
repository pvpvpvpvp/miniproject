package service.client;

import banner.Banner;
import service.userList.UserList;
import service.userjoin.UserJoinImpl;

import java.util.Scanner;

public class Client {
    private boolean isStarted = false;
    public static boolean flag = true;

    public void showMenu() {
        UserList userList = new UserList();
        UserJoinImpl userJoin = new UserJoinImpl();
        int menu;

        Scanner sc = new Scanner(System.in);
        showBanner();

        while (flag) {
            System.out.println("1. 회원 목록 조회");
            System.out.println("2. 회원 가입");
            System.out.println("3. 종료");

            System.out.print("메뉴를 선택해 주세요 : ");

            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    userList.showUserList();
                    break;
                case 2:
                    userJoin.join();
                    break;
                case 3:
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
