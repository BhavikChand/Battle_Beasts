package com.example.battlebeasts;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDatabase.USER_TABLE)
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
