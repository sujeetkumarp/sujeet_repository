/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor.DAO;

import ECUtils.BaseDAO;
import TextEditor.bean.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author SAURABH SAHAB
 */
public class UserDAO extends BaseDAO{
    public static void insert(Users p1){
    Connection con = null;
        try {
            con = getCon();
            String sql = "insert into app_users " +
                    " (u_name, pass, security_ques, answer) "+
                    " values(?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            int i = 1;
            st.setString(i++, p1.getUserNAme());
            st.setString(i++, p1.getPassword());
            st.setString(i++, p1.getSecurityQues());
            st.setString(i++, p1.getAnswer());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
    
    }
    
    public static void update(Users p1){
    Connection con = null;
        try {
            con = getCon();
            String sql = "update app_users "+
                    " set pass = ? "+
                    " where u_name = ? ";
            PreparedStatement st = con.prepareStatement(sql);
            int i = 1;
            st.setString(i++, p1.getPassword());
            st.setString(i++, p1.getUserNAme());
            st.executeUpdate();
        } catch (Exception e) {
       e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
    }
    
    public static void delete(String id){
    Connection con = null;
        try {
            con = getCon();
            String sql = "delete from app_users "+
                    " where id = ? ";
            int i = 1;
            PreparedStatement st = con.prepareCall(sql);
            st.setString(i++, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
    }
    
    public static LinkedList<Users> search(String sc, String si){
        LinkedList<Users> res = new LinkedList<Users>();
        Connection con = null;
        try {
            con = getCon();
            String sql = "select * from app_users where " +sc+" like ? ";
            PreparedStatement st = con.prepareStatement(sql);
            int i = 1;
            st.setString(i++, "%si%");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
            Users p1 = new Users();
            p1.setAnswer(rs.getString("answer"));
            p1.setId(rs.getString("id"));
            p1.setPassword(rs.getString("pass"));
            p1.setSecurityQues(rs.getString("security_ques"));
            p1.setUserNAme(rs.getString("u_name"));
            res.add(p1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
        return res;
    
    }
    
    public static Users findById(String id){
        Users res = null;
        Connection con = null;
        try {
            con = getCon();
            String sql = "select * from app_users where id = ? ";
            PreparedStatement st = con.prepareStatement(sql);
            int i = 1;
            st.setString(i++, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
            Users p1 = new Users();
            p1.setAnswer(rs.getString("answer"));
            p1.setId(rs.getString("id"));
            p1.setPassword(rs.getString("pass"));
            p1.setSecurityQues(rs.getString("security_ques"));
            p1.setUserNAme(rs.getString("u_name"));
            res=p1;
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
        return res;
    
    }
    
    public static Users findByName(String name){
        Users res = null;
        Connection con = null;
        try {
            con = getCon();
            String sql = "select * from app_users where u_name = ? ";
            PreparedStatement st = con.prepareStatement(sql);
            int i = 1;
            st.setString(i++, name);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
            Users p1 = new Users();
            p1.setAnswer(rs.getString("answer"));
            p1.setId(rs.getString("id"));
            p1.setPassword(rs.getString("pass"));
            p1.setSecurityQues(rs.getString("security_ques"));
            p1.setUserNAme(rs.getString("u_name"));
            res=p1;
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
        return res;
    
    }
    
     public static Users validate(String uname, String upass){
        Users res = null;
        Connection con = null;
        try {
            con = getCon();
            String sql = "select * from app_users where u_name = ? and pass = ? ";
            PreparedStatement st = con.prepareStatement(sql);
            int i = 1;
            st.setString(i++, uname);
            st.setString(i++, upass);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Users p1 = new Users();
                p1.setAnswer(rs.getString("answer"));
                p1.setId(rs.getString("id"));
                p1.setPassword(rs.getString("pass"));
                p1.setSecurityQues(rs.getString("security_ques"));
                p1.setUserNAme(rs.getString("u_name"));
                res=p1;
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
        return res;
    
    }
    
    
}
