package com.example.battlebeasts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;

import com.example.battlebeasts.databinding.ActivityMainBinding;
import com.example.battlebeasts.db.AppDataBase;
import com.example.battlebeasts.db.BattleLogDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  ActivityMainBinding binding;

  Button mSignIn;
  Button mSignUp;
  Button mDemoPublicUser;
  Button mDemoAccountUser;

  //Reference to BattleLogDao
  //Reference to Database
  //Abstraction Layer
  //TODO Replace with room repository
  BattleLogDAO mBattleLogDAO;

  List<BattleLog> mBattleLogList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //gives us the binding object that allow us to access all of the fields
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    mSignIn = binding.homePageSignInButton;
    mSignUp = binding.homePageSignUpButton;
    mDemoPublicUser = binding.homePageDemoPublicUserButton;
    mDemoAccountUser = binding.homePageDemoAccountUserButton;

    //Now have access to Object
    mBattleLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().BattleLogDAO();
  }

  private void refreshDisplay() {
    mBattleLogList = mBattleLogDAO.getBattleLogs();
    if (mBattleLogList.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (BattleLog log : mBattleLogList) {
        sb.append(log.toString());
      }
    } else {
//      https://youtu.be/9aY3HcdUfiw?t=838
//      mMainDisplay.setText("No logs yet, time to hit the gym");
    }
  }
}