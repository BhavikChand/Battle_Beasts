package com.example.battlebeasts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.battlebeasts.db.AppDataBase;
import com.example.battlebeasts.db.BattleLogDAO;

import java.util.List;

public class SignIn extends AppCompatActivity {

  private EditText mUserNameField;
  private EditText mPasswordField;

  private Button mButton;

  private BattleLogDAO mBattleLogDAO;

  private String mUsername;
  private String mPassword;
  private User mUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    wireUpDisplay();

    getDataBase();
  }

  private void wireUpDisplay() {
    mUserNameField = findViewById(R.id.editTextSignInUserName);
    mUserNameField = findViewById(R.id.editTextSignInPassword);

    mButton = findViewById(R.id.signInPageSignInButton);

    mButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getValuesFromDisplay();
        if (checkForUserInDatabase()) {
          if (!validatePassword()) {
            Toast.makeText(SignIn.this, "Invalid Password", Toast.LENGTH_SHORT).show();
          } else {
            Intent intent = MainActivity.intentFactory(getApplicationContext(), mUser.getUserId());
            startActivity(intent);
          }
        }
      }
    });
  }

  private boolean validatePassword() {
    return mUser.getPassword().equals(mPassword);
  }

  private void getValuesFromDisplay() {
    mUsername = mUserNameField.getText().toString();
    mPassword = mPasswordField.getText().toString();
  }

  private boolean checkForUserInDatabase() {
    //TODO Not sure why this is working. Going to ask TA.
    mUser = mBattleLogDAO.getUserByUserName(mUsername);
    if (mUser == null) {
      Toast.makeText(this, "no user " + mUsername + " found", Toast.LENGTH_SHORT).show();
      return false;
    }

    return true;
  }

  private void getDataBase() {
    mBattleLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().BattleLogDAO();
  }

  //Intent Factory: Go from Log In Activity to Game Battle Beasts Menu
  public static Intent intentFactory(Context context) {
    Intent intent = new Intent(context, SignIn.class);

    return intent;
  }


}