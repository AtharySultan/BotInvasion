/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author B
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.*;

@Entity
@Table(name="USER_INFO")
public class USER_INFO implements java.io.Serializable{
    
    @Id
     @Column(name="userID")
     private int userID;
     @Column(name="userName")
     private String userName;
     @Column(name="userEmail")
     private String userEmail;
     @Column(name="userPassword")
     private String userPassword;
     static int curUserIndex = -10;
     static ObservableList<USER_INFO> uList = FXCollections.observableArrayList();
     
     


    public USER_INFO(String userName, String userEmail, String userPassword) {
        
        if(uList.isEmpty()){
            userID = 1;
        }
        else{
            userID = (uList.get(uList.size() - 1).getUserID())+1;
        }
        
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        
        
    }
    
    public USER_INFO() {
        this.userID = -10;
        this.userName = null;
        this.userEmail = null;
        this.userPassword = null;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public static void uListSetter() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session = HibernateUtil.getSessionFactory().openSession();
        String queryStr = "from USER_INFO";
        Query query = session.createQuery(queryStr);
        List aList = query.list();
        session.close();
        uList.addAll(aList);
    }

    public static int getCurUserIndex() {
        return curUserIndex;
    }

    public static void setCurUserIndex(int curUserIndex) {
        USER_INFO.curUserIndex = curUserIndex;
    }
    
    
    
    
}
