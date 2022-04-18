package config;

import service.userjoin.CharNumRegExp;
import service.userjoin.PwRegExp;
import service.userjoin.UserJoin;
import service.userjoin.UserJoinImpl;

public class Config {
    public static PwRegExp pwRegExpInstance;
    public static UserJoin userJoinInstance;
    public static final String USER_REPO = "userRepo.txt";

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

}
