
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author B
 */

 @Entity
@Table(name="Levels")
public class LEVELS implements java.io.Serializable {
    @Id
    @Column(name="levelID")
    private int levelID;
     
    @Column(name="levelName")
    private String levelName;
    
    @Column(name="numOfPuzzles")
    private int numOfPuzzles;
    
    @Column(name="numOfTokens")
    private int numOfTokens;
    
    @Column(name="collectedTKNs")
    private int collectedTKNs;

    public LEVELS(int levelID, String levelName, int numOfPuzzles, int numOfTokens, Integer collectedTKNs) {
        this.levelID = levelID;
        this.levelName = levelName;
        this.numOfPuzzles = numOfPuzzles;
        this.numOfTokens = numOfTokens;
        this.collectedTKNs = collectedTKNs;
        
        
    }
    
    public LEVELS() {
        
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getNumOfPuzzles() {
        return numOfPuzzles;
    }

    public void setNumOfPuzzles(int numOfPuzzles) {
        this.numOfPuzzles = numOfPuzzles;
    }

    public int getNumOfTokens() {
        return numOfTokens;
    }

    public void setNumOfTokens(int numOfTokens) {
        this.numOfTokens = numOfTokens;
    }

    public Integer getCollectedTKNs() {
        return collectedTKNs;
    }

    public void setCollectedTKNs(Integer collectedTKNs) {
        this.collectedTKNs = collectedTKNs;
    }
}
