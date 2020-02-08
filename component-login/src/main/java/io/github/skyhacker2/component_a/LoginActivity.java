package io.github.skyhacker2.component_a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.github.skyhacker2.export_api.component_account.ComponentAccount;
import io.github.skyhacker2.export_api.component_account.data.User;
import io.github.skyhacker2.navigation.navigator.ComponentManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class LoginActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;


    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        compositeDisposable = new CompositeDisposable();

        AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
            @Override
            public void run() {

                ComponentAccount componentAccount = ComponentManager.component(ComponentAccount.class);
                if (componentAccount != null) {
                    compositeDisposable.add(componentAccount.login().subscribe(new Consumer<User>() {
                        @Override
                        public void accept(User user) throws Exception {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    }));
                } else {
                    Toast.makeText(LoginActivity.this, "No Component Account", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
