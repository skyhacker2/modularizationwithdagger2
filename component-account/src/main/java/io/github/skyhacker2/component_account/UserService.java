package io.github.skyhacker2.component_account;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import io.github.skyhacker2.export_api.component_account.data.User;
import io.github.skyhacker2.export_api.component_account.data.UserState;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

class UserService {

    private boolean hasLogged = false;
    private User user = null;
    private BehaviorSubject<UserState> userStateBehaviorSubject = BehaviorSubject.create();
    private Context context;

    public UserService(Context context) {
        this.context = context;
        userStateBehaviorSubject.onNext(UserState.NOT_LOGIN);
    }

    public boolean isLogin() {
        return hasLogged;
    }

    public User getUser() {
        return user;
    }

    public Single<User> login() {
        return Single.create(new SingleOnSubscribe<User>() {
            @Override
            public void subscribe(SingleEmitter<User> emitter) throws Exception {
                user = new User("eleven", "123456789", 18);
                emitter.onSuccess(user);
                userStateBehaviorSubject.onNext(UserState.LOGIN);

            }
        }).delay(1000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Boolean> logout() {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {
                emitter.onSuccess(true);
                userStateBehaviorSubject.onNext(UserState.NOT_LOGIN);
            }
        }).delay(1000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<UserState> observeUserState() {
        return userStateBehaviorSubject.hide().observeOn(AndroidSchedulers.mainThread());
    }

}
