package com.example.battlebeasts;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.battlebeasts.db.AppDataBase;

@Entity(tableName = AppDataBase.BATTLELOG_TABLE)
public class BattleLog {
  @PrimaryKey(autoGenerate = true)
  private int mLogId;
  private int mUserId;
  private String  mPlayerName;
  private String mOpponentName;
  private String mPlayerAttack;
  private String mOpponentAttack;
  //0 is Not Mega Damage...
  // 1 display nothing
  // 2 Mega Damage!!
  private int mMegaEffectivePlayerAttack = -1;
  private int mMegaEffectiveOpponentAttack = -1;
  //0 Player Attacking Opponent
  //1 Opponent Attacking Player
  private int mTurnCheck = -1;
  private int mTurnCount;
  private int mPlayerHp;
  private int mOpponentHp;
  private int mHpQuotientPlayerHpDmg;
  private int mHpQuotientOpponentHpDmg;

  public BattleLog(int logId, int userId, String playerName, String opponentName, String playerAttack, String opponentAttack, int turnCount, int playerHp, int opponentHp, int hpQuotientPlayerHpDmg, int hpQuotientOpponentHpDmg) {
    mLogId = logId;
    mUserId = userId;
    mPlayerName = playerName;
    mOpponentName = opponentName;
    mPlayerAttack = playerAttack;
    mOpponentAttack = opponentAttack;
    mTurnCount = turnCount;
    mPlayerHp = playerHp;
    mOpponentHp = opponentHp;
    mHpQuotientPlayerHpDmg = hpQuotientPlayerHpDmg;
    mHpQuotientOpponentHpDmg = hpQuotientOpponentHpDmg;
  }

  //Display Log of Battle
  @Override
  public String toString() {
    //With Death (DO NOT DISPLAY NEXT ATTACK) (Requires Death Text)
    //Player Player Turn Player Mega Effective Attack Check

    /**
     * Might need to add mLogId to toString, Check Part 2 in video here: https://www.youtube.com/watch?v=2fArFXH5_MA
     */

    if (mTurnCheck == 0 && mMegaEffectivePlayerAttack == 2 && mOpponentHp == 0) {
      return "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Turn Count: " + mTurnCount + " =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + "\n" +
              mPlayerName + " used " + mPlayerAttack + "!" + "\n" +
              "It's mega effective!!" + "\n" +
              "(The opposing " + mOpponentName + " lost " + mHpQuotientOpponentHpDmg + "% of its health!)" + "\n" +
              "\n" +
              "The opposing " + mOpponentName + " passed out!" + "\n";
    };
    if (mTurnCheck == 0 && mMegaEffectivePlayerAttack == 1 && mOpponentHp == 0) {
      return "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Turn Count: " + mTurnCount + " =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + "\n" +
              mPlayerName + " used " + mPlayerAttack + "!" + "\n" +
              "(The opposing " + mOpponentName + " lost " + mHpQuotientOpponentHpDmg + "% of its health!)" + "\n" +
              "\n" +
              "The opposing " + mOpponentName + " passed out!" + "\n";
    };
    if (mTurnCheck == 0 && mMegaEffectivePlayerAttack == 0 && mOpponentHp == 0) {
      return "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Turn Count: " + mTurnCount + " =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + "\n" +
              mPlayerName + " used " + mPlayerAttack + "!" + "\n" +
              "Not very mega damage..." + "\n" +
              "(The opposing " + mOpponentName + " lost " + mHpQuotientOpponentHpDmg + "% of its health!)" + "\n" +
              "\n" +
              "The opposing " + mOpponentName + " passed out!" + "\n";
    };

    //Without Death (Requires The Next Attack to be Displayed)
    //Player Player Turn Player Mega Effective Attack Check
    if (mTurnCheck == 0 && mMegaEffectivePlayerAttack == 2 && mMegaEffectiveOpponentAttack == 2) {
      return "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Turn Count: " + mTurnCount + " =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + "\n" +
              //Player Attack Start
              mPlayerName + " used " + mPlayerAttack + "!" + "\n" +
              "It's mega effective!!" + "\n" +
              "(The opposing " + mOpponentName + " lost " + mHpQuotientOpponentHpDmg + "% of its health!)" + "\n" +
              "\n" +
              "The opposing " + mOpponentName + " passed out!" + "\n" +
              "\n" +
              //Opponent Attack Start
              mOpponentAttack + " used " + mOpponentAttack + "!" + "\n" +
              "It's mega effective!!" + "\n" +
              "(The opposing " + mPlayerName + " lost " + mHpQuotientPlayerHpDmg + "% of its health!)" + "\n" +
              "\n" +
              "My " + mPlayerName + " passed out!" + "\n";

    };
    if (mTurnCheck == 0 && mMegaEffectivePlayerAttack == 2 && mMegaEffectiveOpponentAttack == 1) {
      return "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Turn Count: " + mTurnCount + " =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + "\n" +
              mPlayerName + " used " + mPlayerAttack + "!" + "\n" +
//              "(The opposing " + mOpponentName + " lost " + mHpQuotient + "% of its health!)" + "\n" +
              "\n";
    };
    if (mTurnCheck == 0 && mMegaEffectivePlayerAttack == 2 && mMegaEffectiveOpponentAttack == 0) {
      return "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- Turn Count: " + mTurnCount + " =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + "\n" +
              mPlayerName + " used " + mPlayerAttack + "!" + "\n" +
              "Not very mega damage..." + "\n" +
//              "(The opposing " + mOpponentName + " lost " + mHpQuotient + "% of its health!)" + "\n" +
              "\n";
    };

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-==-==-=-=-=-=-=-=-=-=-=-=
    //With Death
    //Opponent Outspeeds Player Mega Effective Check

    //Without Death
    //Player Outspeeds Opponent Mega Effective Check




    return "toString in BattleLog.java failed. Bhavik you missed a condition in BattleLog.java file";
  }

  public int getLogId() {
    return mLogId;
  }

//  public void setLogId(int logId) {
//    mLogId = logId;
//  }

  public int getUserId() {
    return mUserId;
  }

//  public void setUserId(int userId) {
//    mUserId = userId;
//  }


  public void setLogId(int logId) {
    mLogId = logId;
  }

  public void setUserId(int userId) {
    mUserId = userId;
  }

  public String getPlayerName() {
    return mPlayerName;
  }

  public void setPlayerName(String playerName) {
    mPlayerName = playerName;
  }

  public String getOpponentName() {
    return mOpponentName;
  }

  public void setOpponentName(String opponentName) {
    mOpponentName = opponentName;
  }

  public String getPlayerAttack() {
    return mPlayerAttack;
  }

  public void setPlayerAttack(String playerAttack) {
    mPlayerAttack = playerAttack;
  }

  public String getOpponentAttack() {
    return mOpponentAttack;
  }

  public void setOpponentAttack(String opponentAttack) {
    mOpponentAttack = opponentAttack;
  }

  public int getMegaEffectivePlayerAttack() {
    return mMegaEffectivePlayerAttack;
  }

  public void setMegaEffectivePlayerAttack(int megaEffectivePlayerAttack) {
    mMegaEffectivePlayerAttack = megaEffectivePlayerAttack;
  }

  public int getMegaEffectiveOpponentAttack() {
    return mMegaEffectiveOpponentAttack;
  }

  public void setMegaEffectiveOpponentAttack(int megaEffectiveOpponentAttack) {
    mMegaEffectiveOpponentAttack = megaEffectiveOpponentAttack;
  }

  public int getTurnCheck() {
    return mTurnCheck;
  }

  public void setTurnCheck(int turnCheck) {
    mTurnCheck = turnCheck;
  }

  public int getTurnCount() {
    return mTurnCount;
  }

  public void setTurnCount(int turnCount) {
    mTurnCount = turnCount;
  }

  public int getPlayerHp() {
    return mPlayerHp;
  }

  public void setPlayerHp(int playerHp) {
    mPlayerHp = playerHp;
  }

  public int getOpponentHp() {
    return mOpponentHp;
  }

  public void setOpponentHp(int opponentHp) {
    mOpponentHp = opponentHp;
  }

  public int getHpQuotientPlayerHpDmg() {
    return mHpQuotientPlayerHpDmg;
  }

  public void setHpQuotientPlayerHpDmg(int hpQuotientPlayerHpDmg) {
    mHpQuotientPlayerHpDmg = hpQuotientPlayerHpDmg;
  }

  public int getHpQuotientOpponentHpDmg() {
    return mHpQuotientOpponentHpDmg;
  }

  public void setHpQuotientOpponentHpDmg(int hpQuotientOpponentHpDmg) {
    mHpQuotientOpponentHpDmg = hpQuotientOpponentHpDmg;
  }
}
