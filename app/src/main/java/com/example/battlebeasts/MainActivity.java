package com.example.battlebeasts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.battlebeasts.databinding.ActivityMainBinding;
import com.example.battlebeasts.db.AppDataBase;
import com.example.battlebeasts.db.BattleLogDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  private Button buttonSignInMainAcitivity;
  private Button buttonSignUpMainAcitivity;
  private Button buttonDemoPublicUserMainAcitivity;
  private Button buttonDemoAccountUserMainAcitivity;

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
  private SharedPreferences mPreferences = null;
  private User mUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //Add this line to all .java
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    setContentView(R.layout.activity_main);

    //Coding in Flow:
    buttonSignInMainAcitivity = (Button) findViewById(R.id.homePageSignInButton);
    buttonSignUpMainAcitivity = (Button) findViewById(R.id.homePageSignUpButton);
    buttonDemoPublicUserMainAcitivity = (Button) findViewById(R.id.homePageDemoPublicUserButton);
    buttonDemoAccountUserMainAcitivity = (Button) findViewById(R.id.homePageDemoAccountUserButton);

    //On Click Listeners for Buttons:
    //Sign In Button:
    buttonSignInMainAcitivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openSignInActivity();
      }
    });

    //Sign Up Button:
    buttonSignUpMainAcitivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openSignUpActivity();
      }
    });

    //TODO: Need Automatic Sign In for Public user (No Account Public Teams)
    //Demo Public User:
    buttonDemoPublicUserMainAcitivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openBattleBeastsMenu();
      }
    });

    //TODO: Need Automatic Sign In For Account User (Account Public Teams Access)
    //Demo Account User:
    buttonDemoAccountUserMainAcitivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openBattleBeastsMenu();
      }
    });

    //gives us the binding object that allow us to access all of the fields
//    binding = ActivityMainBinding.inflate(getLayoutInflater());
//    setContentView(binding.getRoot());
//
//    mSignIn = binding.homePageSignInButton;
//    mSignUp = binding.homePageSignUpButton;
//    mDemoPublicUser = binding.homePageDemoPublicUserButton;
//    mDemoAccountUser = binding.homePageDemoAccountUserButton;

//    getDatabase();

//    checkForUser();
//    addUserToPreference(mUserId);
//    loginUser(mUserId);



//    refreshDisplay();

    //TODO Not sure if I need Sign in button here
//    mSignIn.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        signInBattleLog();
//        refreshDisplay();
//      }
//    });
  }//end of onCreate =================================================================

  //Room Database
  private void getDatabase() {
    mBattleLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().BattleLogDAO();
  }

  //Intent Factories: (I believe this is "Wiring Up Buttons")
  public void openSignInActivity() {
    Intent intent = new Intent(this, SignIn.class);
    startActivity(intent);
  }

  public void openSignUpActivity() {
    Intent intent = new Intent(this, SignUp.class);
    startActivity(intent);
  }

  public void openBattleBeastsMenu() {
    Intent intent = new Intent(this, BattleBeastsMenuPage.class);
    startActivity(intent);
  }

  //Button On Click Listeners

  //Intent Factory for Buttons



  //Moved Everything here to BattleBeastsMenuPage.java
//  private void loginUser(int userId) {
//    mUser = mBattleLogDAO.getUserByUserId(userId);
//    invalidateOptionsMenu();
//  }
//
//  @Override
//  public boolean onPrepareOptionsMenu(Menu menu) {
//    if (mUser != null) {
//      MenuItem item = menu.findItem(R.id.buttonUserMenuSignOut);
//      item.setTitle(mUser.getUserName());
//    }
//    return super.onPrepareOptionsMenu(menu);
//  }
//
//  private void logoutUser() {
//    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//
//    alertBuilder.setMessage(R.string.sign_out);
//
//    alertBuilder.setPositiveButton(getString(R.string.yes),
//            new DialogInterface.OnClickListener() {
//              @Override
//              public void onClick(DialogInterface dialogInterface, int i) {
//                clearUserFromIntent();;
//                clearUserFromPref();
//                mUserId = -1;
//                checkForUser();
//              }
//            });
//    alertBuilder.setNegativeButton(getString(R.string.no),
//            new DialogInterface.OnClickListener() {
//              @Override
//              public void onClick(DialogInterface dialogInterface, int which) {
//                //We don't really need to do anything here.
//              }
//            });
//
//    alertBuilder.create().show();
//  }
//
//  private void addUserToPreference(int userId) {
//    if (mPreferences == null) {
//      getPrefs();
//    }
//    SharedPreferences.Editor editor = mPreferences.edit();
//    editor.putInt(USER_ID_KEY, userId);
//  }
//
//
//
//  private void checkForUser() {
//    //Do we have a user in the intent?
//    mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
//
//    //Do we have a user in the preferences?
//    if (mUserId != -1) {
//      return;
//    }
//
//    if (mPreferences == null) {
//      getPrefs();
//    }
//
//    mUserId = mPreferences.getInt(USER_ID_KEY, -1);
//
//    if (mUserId != -1) {
//      return;
//    }
//
//    //Do we have any users at all?
//    List<User> users = mBattleLogDAO.getAllBattleUsers();
//    if (users.size() <= 0) {
//      User defaultUser = new User("Sudo Nim", "password123", -1);
//      mBattleLogDAO.insert(defaultUser);
//    }
//
//    Intent intent = SignIn.intentFactory(this);
//    startActivity(intent);
//
//
//  }
//
//  private void getPrefs() {
//    mPreferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
//  }
//
//  private void clearUserFromIntent() {
//    getIntent().putExtra(USER_ID_KEY, -1);
//  }
//
//  private void clearUserFromPref() {
//    addUserToPreference(-1);
//  }
//
//  //TODO: Finish this
//  private void addUserToPreference() {
//
//  }
//
//
//
//  //TODO RefreshDisplay Likely different
//  private void refreshDisplay() {
//    mBattleLogList = mBattleLogDAO.getAllBattleLogs();
//    if (mBattleLogList.isEmpty()) {
//      StringBuilder sb = new StringBuilder();
//      for (BattleLog log : mBattleLogList) {
//        sb.append(log.toString());
//      }
////      mMainDisplay.setText(sb.toString());
//    } else {
////      https://youtu.be/9aY3HcdUfiw?t=838
////      mMainDisplay.setText("No logs yet, time to hit the gym");
//    }
//  }
//
//  public static Intent intentFactory(Context context, int userId) {
//    //                         Intent(at, going);
//    Intent intent = new Intent(context, MainActivity.class);
//    intent.putExtra(USER_ID_KEY, userId);
//
//    return intent;
//  }
}