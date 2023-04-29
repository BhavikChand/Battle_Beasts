package com.example.battlebeasts.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.battlebeasts.BattleLog;

//Entry point for our Database
//TODO Create BattleLogDAO Object
@Dao
public interface BattleLogDAO {

  @Insert
  void insert(BattleLog... battleLogs);

  @Update
  void update(BattleLog... battleLogs);

  @Delete
  void delete(BattleLog battleLog);


}
