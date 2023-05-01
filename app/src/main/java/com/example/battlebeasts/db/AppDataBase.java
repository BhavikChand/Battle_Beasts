package com.example.battlebeasts.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Import classes:
import com.example.battlebeasts.BattleLog;
import com.example.battlebeasts.User;

//This is a database access point
//We get access to this through a repository
@Database(entities = {BattleLog.class, User.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
  public static final String DATABASE_NAME = "BattleLog.db";
  public static final String BATTLELOG_TABLE = "battlelog_table";
  public static final String USER_TABLE = "USER_TABLE";

  //Volatile means CAN ONLY ACCESS FROM MAIN MEMORY
  //Not Store DB in Ram, only in Storage to avoid race conditions
  private static volatile AppDataBase instance;
  private static final Object LOCK = new Object();

  //Never implement this, the room wrapper creates this for us
  public abstract BattleLogDAO BattleLogDAO();


  //When working on Part 2 Gym Log he doesn't have this method??
  //Singleton class
  public static AppDataBase getInstance(Context context) {
    if (instance == null) {
      synchronized (LOCK) {
        if (instance == null) {
          instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME).build();
        }
      }
    }

    return instance;
  }
}
