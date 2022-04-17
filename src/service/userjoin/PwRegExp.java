package service.userjoin;

import domain.user.UserData;
import domain.user.UserJoinData;

/**
 * 최소 8 자, 최소 하나의 문자 및 하나의 숫자 : ^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$
 * 최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자 : ^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$
 * 최소 8 자, 대문자 하나 이상, 소문자 하나 및 숫자 하나 : ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$
 * 최소 8 자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상 : ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}
 * 최소 8 자 및 최대 10 자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상 : ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,10}
 *
 */
public interface PwRegExp {
    boolean pwRegExp(UserJoinData userJoinData);
}