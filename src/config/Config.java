package config;

import service.user.*;

public class Config {
    public static PwRegExp pwRegExpInstance;
    public static UserJoin userJoinInstance;
    public static UserDel userDelInstance;
    public static final String USER_REPO = "userRepo.txt";
    public static final String USER_INDEX = "index.txt";

    private Config() {

    }

    public static PwRegExp getPwRegExpInstance() {
        if (pwRegExpInstance == null) {
            pwRegExpInstance = new CharNumRegExp();
        }

        return pwRegExpInstance;
    }

    public static UserJoin getUserJoinInstance() {
        if (userJoinInstance == null) {
            userJoinInstance = new UserJoinImpl();
        }

        return userJoinInstance;
    }

    public static UserDel getUserDelInstance() {
        if (userDelInstance == null) {
            userDelInstance = new UserDelImpl();
        }

        return userDelInstance;
    }

}
