package com.example.battlebeasts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BattleBeastsMenuPage extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_battle_beasts_menu_page);
  }

  public static Intent intentFactory(Context context) {
    Intent intent = new Intent(context, BattleBeastsMenuPage.class);

    return intent;
  }
}