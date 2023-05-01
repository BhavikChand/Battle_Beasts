package com.example.battlebeasts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.battlebeasts.db.AppDataBase;
import com.example.battlebeasts.db.BattleLogDAO;

public class SignIn extends AppCompatActivity {

  private EditText mUserName;
  private EditText mPassword;

  private Button mButton;

  private BattleLogDAO mBattleLogDAO;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    wireUpDisplay();

    getDataBase();
  }

  private void wireUpDisplay() {
    mUserName = findViewById(R.id.editTextSignInUserName);
    mUserName = findViewById(R.id.editTextSignInPassword);

    mButton = findViewById(R.id.signInPageSignInButton);

    mButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //TODO
      }
    });
  }

  private void getDataBase() {
    mBattleLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().BattleLogDAO();
  }

  //Intent Factory: Go from Log In Activity to Game Battle Beasts Menu
  public static Intent intentFactory(Context context) {
    Intent intent = new Intent(context, BattleBeastsMenuPage.class);

    return intent;
  }


}