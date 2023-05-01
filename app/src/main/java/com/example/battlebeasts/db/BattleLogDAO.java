package com.example.battlebeasts.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.battlebeasts.BattleLog;
import com.example.battlebeasts.User;

import java.util.List;

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

  @Query("SELECT * FROM " + AppDataBase.BATTLELOG_TABLE)
  List<BattleLog> getAllBattleLogs();

  @Query("SELECT * FROM " + AppDataBase.BATTLELOG_TABLE + " WHERE mLogId = :logId")
  List<BattleLog> getBattleLogsById(int logId);

  @Query("SELECT * FROM " + AppDataBase.BATTLELOG_TABLE + " WHERE mUserId = :userId")
  List<BattleLog> getBattleLogsByUserId(int userId);

  @Insert
  void insert(User... users);

  @Update
  void update(User... users);

  @Delete
  void delete(User user);

  //I wrote ORDER BY mUserId DESC
  @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " ORDER BY mUserId DESC")
  List<User> getAllBattleUsers();

  @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUserName = :userName")
  List<User> getUserByUserName(String userName);

  @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUserId = :userId")
  List<User> getUserByUserId(int userId);
}
