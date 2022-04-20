package service.user;

import domain.user.UserJoinData;

public class CharNumRegExp implements PwRegExp {
    @Override
    public boolean pwRegExp(UserJoinData userJoinData) {
        String pw = userJoinData.getPw();
        boolean res = pw.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

        return res;
    }
}
