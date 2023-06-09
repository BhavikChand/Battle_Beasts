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
import android.widget.Button;

import com.example.battlebeasts.databinding.ActivityMainBinding;
import com.example.battlebeasts.db.AppDataBase;
import com.example.battlebeasts.db.BattleLogDAO;

import java.util.List;

public class BattleBeastsMenuPage extends AppCompatActivity {
  private static final String USER_ID_KEY = "com.example.battlebeasts.userIdKey";
  private static final String PREFERENCES_KEY = "com.example.battlebeasts.PREFERENCES_KEY";
  private ActivityMainBinding binding;

  private Button mSignIn;
  private Button mSignUp;
  private Button mDemoPublicUser;
  private Button mDemoAccountUser;

  private BattleLogDAO mBattleLogDAO;

  private int mUserId = -1;
  private SharedPreferences mPreferences = null;
  private User mUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    setContentView(R.layout.activity_battle_beasts_menu_page);

    //gives us the binding object that allow us to access all of the fields
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    getDatabase();

    checkForUser();
    addUserToPreference(mUserId);
    loginUser(mUserId);
  }

  //Room Database, not sure if I need this here or in MainActivity.java
  private void getDatabase() {
    mBattleLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().BattleLogDAO();
  }

  public static Intent intentFactory(Context context) {
    Intent intent = new Intent(context, BattleBeastsMenuPage.class);

    return intent;
  }

  private void loginUser(int userId) {
    mUser = mBattleLogDAO.getUserByUserId(userId);
    invalidateOptionsMenu();
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    if (mUser != null) {
      MenuItem item = menu.findItem(R.id.buttonUserMenuSignOut);
      item.setTitle(mUser.getUserName());
    }
    return super.onPrepareOptionsMenu(menu);
  }

  private void logoutUser() {
    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

    alertBuilder.setMessage(R.string.sign_out);

    alertBuilder.setPositiveButton(getString(R.string.yes),
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                clearUserFromIntent();;
                clearUserFromPref();
                mUserId = -1;
                checkForUser();
              }
            });
    alertBuilder.setNegativeButton(getString(R.string.no),
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int which) {
                //We don't really need to do anything here.
              }
            });

    alertBuilder.create().show();
  }

  private void addUserToPreference(int userId) {
    if (mPreferences == null) {
      getPrefs();
    }
    SharedPreferences.Editor editor = mPreferences.edit();
    editor.putInt(USER_ID_KEY, userId);
  }



  private void checkForUser() {
    //Do we have a user in the intent?
    mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);

    //Do we have a user in the preferences?
    if (mUserId != -1) {
      return;
    }

    if (mPreferences == null) {
      getPrefs();
    }

    mUserId = mPreferences.getInt(USER_ID_KEY, -1);

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

  private void getPrefs() {
    mPreferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
  }

  private void clearUserFromIntent() {
    getIntent().putExtra(USER_ID_KEY, -1);
  }

  private void clearUserFromPref() {
    addUserToPreference(-1);
  }

  //TODO: Finish this
  private void addUserToPreference() {

  }
}