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

  //Constructors


  public User(String userName, String password, int teamId) {
    mUserName = userName;
    mPassword = password;
    mTeamId = teamId;
  }

//  public User(String userName, String password, int teamId, String avatarImgUrl) {
//    mUserName = userName;
//    mPassword = password;
//    mTeamId = teamId;
//    mAvatarImgUrl = avatarImgUrl;
//  }

  //Getters and Setters
  public int getUserId() {
    return mUserId;
  }

  public void setUserId(int userId) {
    mUserId = userId;
  }

  public String getUserName() {
    return mUserName;
  }

  public void setUserName(String userName) {
    mUserName = userName;
  }

  public String getPassword() {
    return mPassword;
  }

  public void setPassword(String password) {
    mPassword = password;
  }

  public int getTeamId() {
    return mTeamId;
  }

  public void setTeamId(int teamId) {
    mTeamId = teamId;
  }

  public String getAvatarImgUrl() {
    return mAvatarImgUrl;
  }

  public void setAvatarImgUrl(String avatarImgUrl) {
    mAvatarImgUrl = avatarImgUrl;
  }
}
