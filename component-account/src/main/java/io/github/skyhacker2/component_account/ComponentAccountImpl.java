package io.github.skyhacker2.component_account;

import android.content.Context;

import io.github.skyhacker2.export_api.component_account.ComponentAccount;
import io.github.skyhacker2.export_api.component_account.data.User;
import io.github.skyhacker2.export_api.component_account.data.UserState;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ComponentAccountImpl implements ComponentAccount {

    private UserService userService;

    @Override
    public void init(Context context) {
        userService = new UserService(context);
    }

    @Override
    public boolean isLogin() {
        return userService.isLogin();
    }

    @Override
    public User getUser() {
        return userService.getUser();
    }

    @Override
    public Single<User> login() {
        return userService.login();
    }

    @Override
    public Single<Boolean> logout() {
        return userService.logout();
    }

    @Override
    public Observable<UserState> observeUserState() {
        return userService.observeUserState();
    }

    @Override
    public String getName() {
        return "ComponentAccountImpl";
    }
}
