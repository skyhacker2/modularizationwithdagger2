package io.github.skyhacker2.home_component;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.github.skyhacker2.export_api.component_account.ComponentAccount;
import io.github.skyhacker2.export_api.component_account.data.User;
import io.github.skyhacker2.export_api.component_account.data.UserState;
import io.github.skyhacker2.export_api.component_login.ComponentLogin;
import io.github.skyhacker2.navigation.navigator.ComponentManager;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class HomeActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        compositeDisposable = new CompositeDisposable();

        final ComponentAccount componentAccount = ComponentManager.component(ComponentAccount.class);
        if (componentAccount != null) {
            compositeDisposable.add(componentAccount.observeUserState().subscribe(new Consumer<UserState>() {
                @Override
                public void accept(UserState userState) throws Exception {
                    Toast.makeText(HomeActivity.this, "UserState=" + userState, Toast.LENGTH_SHORT).show();
                    User user = componentAccount.getUser();
                    TextView userInfo = findViewById(R.id.user_info);
                    if (user != null) {
                        userInfo.setText(user.toString());
                    } else {
                        userInfo.setText("No Login");
                    }
                }
            }));

        }

        findViewById(R.id.call_component_login).setOnClickListener(onClickListener);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.call_component_login) {
                ComponentLogin loginComponent = ComponentManager.component(ComponentLogin.class);
                if (loginComponent != null) {
                    loginComponent.goToLogin(HomeActivity.this);
                } else {
                    Toast.makeText(HomeActivity.this, "No Component A", Toast.LENGTH_SHORT).show();
                }
            }

        }
    };
}
