package io.github.skyhacker2.export_api.component_account;

import android.content.Context;

import io.github.skyhacker2.export_api.IComponent;
import io.github.skyhacker2.export_api.component_account.data.User;
import io.github.skyhacker2.export_api.component_account.data.UserState;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface ComponentAccount extends IComponent {


    void init(Context context);

    boolean isLogin();

    User getUser();

    Single<User> login();

    Single<Boolean> logout();

    Observable<UserState> observeUserState();
}
