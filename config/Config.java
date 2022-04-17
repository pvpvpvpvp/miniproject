package config;

import service.userjoin.CharNumRegExp;
import service.userjoin.PwRegExp;

public class Config {
    public static PwRegExp pwRegExpInstance;

    private Config() {

    }

    public static PwRegExp getPwRegExpInstance() {
        if (pwRegExpInstance == null) {
            pwRegExpInstance = new CharNumRegExp();
        }

        return pwRegExpInstance;
    }

}
