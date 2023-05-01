package com.example.battlebeasts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.battlebeasts.databinding.ActivityMainBinding;
import com.example.battlebeasts.db.AppDataBase;
import com.example.battlebeasts.db.BattleLogDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  private static final String USER_ID_KEY = "com.example.battlebeasts.userIdKey";
  private static final String PREFERENCES_KEY = "com.example.battlebeasts.PREFERENCES_KEY";
  private ActivityMainBinding binding;

  private Button mSignIn;
  private Button mSignUp;
  private Button mDemoPublicUser;
  private Button mDemoAccountUser;

  //Reference to BattleLogDao
  //Reference to Database
  //Abstraction Layer
  //TODO Replace with room repository
  private BattleLogDAO mBattleLogDAO;

  private List<BattleLog> mBattleLogList;

  private int mUserId = -1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getDatabase();

    checkForUser();

    //gives us the binding object that allow us to access all of the fields
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    mSignIn = binding.homePageSignInButton;
    mSignUp = binding.homePageSignUpButton;
    mDemoPublicUser = binding.homePageDemoPublicUserButton;
    mDemoAccountUser = binding.homePageDemoAccountUserButton;

    refreshDisplay();

    //TODO Not sure if I need Sign in button here
//    mSignIn.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        signInBattleLog();
//        refreshDisplay();
//      }
//    });
  }//end of onCreate

  private void getDatabase() {
    mBattleLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().BattleLogDAO();
  }

  private void checkForUser() {
    //Do we have a user in the intent?
    mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);

    //Do we have a user in the preferences?
    if (mUserId != -1) {
      return;
    }

    SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    mUserId = preferences.getInt(USER_ID_KEY, -1);

    if (mUserId != -1) {
      return;
    }

    //Do we have any users at all?
    List<User> users = mBattleLogDAO.getAllBattleUsers();
    if (users.size() <= 0) {
      User defaultUser = new User("Sudo Nim", "password123", -1);
      mBattleLogDAO.insert(defaultUser);
    }

    Intent intent = SignIn.intentFactory(this);
    startActivity(intent);


  }

  //TODO Not sure if I need Sign in button here
//  private void signInBattleLog() {
//
//  }


  //TODO RefreshDisplay Likely different
  private void refreshDisplay() {
    mBattleLogList = mBattleLogDAO.getAllBattleLogs();
    if (mBattleLogList.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (BattleLog log : mBattleLogList) {
        sb.append(log.toString());
      }
//      mMainDisplay.setText(sb.toString());
    } else {
//      https://youtu.be/9aY3HcdUfiw?t=838
//      mMainDisplay.setText("No logs yet, time to hit the gym");
    }
  }

  public static Intent intentFactory(Context context, int userId) {
    Intent intent = new Intent(context, MainActivity.class);
    intent.putExtra(USER_ID_KEY, userId);

    return intent;
  }
}