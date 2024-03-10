
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author B
 */
@Entity
@Table(name = "SCORE")
public class SCORE implements java.io.Serializable {

    @Id
    @Column(name = "userID")
    private int userID;
    @Column(name = "levelID")
    private int levelID;
    @Column(name="collectedTokens")
    private int collectedTokens;
    @Column(name="completedLvl")
    private int completedLvl;
    @Column(name="prize")
    private String prize;
    /*LEVELS lvl1;
    LEVELS lvl2;
    LEVELS lvl3;*/
    
    

    public SCORE(int newUserID) {
        this.userID = newUserID;
        /*lvl1 = new LEVELS(1,"Level 1",3,3,0);
        lvl2 = new LEVELS(2,"Level 2",3,3,0);
        lvl3 = new LEVELS(3,"Level 3",3,4,0);*/
        levelID = 1;
        collectedTokens = 0;
        completedLvl = 0;
        prize = "NO";
        
        
    }
    public SCORE() {
        
    }
    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public int getCollectedTokens() {
        return collectedTokens;
    }

    public void setCollectedTokens(int collectedTokens) {
        this.collectedTokens = collectedTokens;
    }

    public int getCompletedLvl() {
        return completedLvl;
    }

    public void setCompletedLvl(int completedLvl) {
        this.completedLvl = completedLvl;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    

}
