package com.web.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.objects.user_user_profile;
import com.web.util.Dbmanager;

public class user_user_profile_dao {
	private static final String INSERT_USERS ="INSERT INTO USER_USER_PROFILE VALUES(?,?,?,SYSDATE(),?,?)";
	private static final String UPDATE_USERS ="UPDATE USER_USER_PROFILE SET PASSWORD=?,USER_ID=? WHERE USERNAME=?";
	private static final String DELETE_USERS ="DELETE FROM USER_USER_PROFILE WHERE USER_ID=?";
	private static final String CHECK_USER   ="SELECT * FROM USER_USER_PROFILE WHERE USER_ID=?";
	private static final String SELECT_USERS_BY_ID ="SELECT USERNAME,PASSWORD,ROLE,USER_ID FROM USER_USER_PROFILE WHERE USER_ID=?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM USER_USER_PROFILE";
	Connection con =null;
	
	public boolean insertUser(user_user_profile user) {
		
		boolean rowsaffected = false;
		System.out.println(INSERT_USERS);
		try {
			con=Dbmanager.getConnection();
			PreparedStatement ps =con.prepareStatement(INSERT_USERS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, "Guest");
			ps.setString(4, user.getUser_id());
			ps.setString(5, "Others");
			
			rowsaffected=ps.executeUpdate() > 0 ;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowsaffected;
	}
	
	public boolean updateUser(user_user_profile user) {
		
		boolean rowsaffected = false;
		System.out.println(UPDATE_USERS) ;
		try {
			con=Dbmanager.getConnection() ;
			PreparedStatement ps =con.prepareStatement(UPDATE_USERS) ;
			ps.setString(1 , user.getUsername()) ;
			ps.setString(2 , user.getPassword()) ;
			ps.setString(3 , "Guest") ;
			ps.setString(4 , user.getUser_id()) ;
			ps.setString(5 , "others") ;
			rowsaffected = ps.executeUpdate() > 0 ;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowsaffected;
	}
	
	public boolean deleteUser(String id) {
		
		boolean rowsaffected = false;
		System.out.println(DELETE_USERS);
		try {
			con=Dbmanager.getConnection();
			PreparedStatement ps =con.prepareStatement(DELETE_USERS) ;
			ps.setString(1,id);
			rowsaffected=ps.executeUpdate()>0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowsaffected;
	}
	
	public user_user_profile selectUser(String id) {
		
		System.out.println(SELECT_USERS_BY_ID);
		user_user_profile u =new user_user_profile();
		try {
			con=Dbmanager.getConnection();
			PreparedStatement ps =con.prepareStatement(SELECT_USERS_BY_ID);
			ps.setString(1,id);
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				u.setUsername(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setRole(rs.getString(3));
				u.setUser_id(rs.getString(4));
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	public List<user_user_profile> getAllUsers(){
		List<user_user_profile> u = new ArrayList<>();
		
		user_user_profile u1 =null;
		try {
			con=Dbmanager.getConnection();
			PreparedStatement p =con.prepareStatement(SELECT_ALL_USERS);
			ResultSet rs =p.executeQuery();
			while(rs.next()) {
				u1 = new user_user_profile();
				u1.setUsername(rs.getString(1));
				u1.setPassword(rs.getString(2));
				u1.setRole(rs.getString(3));
				u1.setCreate_time(rs.getString(4));
				u1.setUser_id(rs.getString(5));
				u1.setCreated_user(rs.getString(6));
				u.add(u1);
			}
		}catch(Exception e) {
				e.printStackTrace();
			}
		return u;
		}
	public boolean checkUser(String id) {
		
		boolean rowsaffected = false;
		System.out.println(CHECK_USER);
		try {
			
			con=Dbmanager.getConnection();
			PreparedStatement ps =con.prepareStatement(CHECK_USER);
			ps.setString(1,id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				rowsaffected=true;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowsaffected;
	}
}
