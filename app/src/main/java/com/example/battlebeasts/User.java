package com.example.battlebeasts;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.battlebeasts.db.AppDataBase;

@Entity(tableName = AppDataBase.USER_TABLE)
public class User {

  @PrimaryKey(autoGenerate = true)
  //Necessary
  private int mUserId;
  private String mUserName;
  private String mPassword;

  //Project Specific (Users Table)
  private int mTeamId;
  private String mAvatarImgUrl;

}
