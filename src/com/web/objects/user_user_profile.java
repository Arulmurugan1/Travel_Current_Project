package com.web.objects;

import java.time.LocalDateTime;

public class user_user_profile {
		private String username;
		private String password;
		private String role;
		private String create_time;
		private String user_id;
		private String created_user;
		private LocalDateTime last_login;
		public user_user_profile() {
			super();
			
		}
		
		
		public user_user_profile(String username, String password, String role, String user_id, String created_user,LocalDateTime last_login) {
			super();
			this.username = username;
			this.password = password;
			this.role = role;
			this.user_id = user_id;
			this.created_user=created_user;
			this.last_login = last_login;
		}
		
		

		public user_user_profile(String username, String password, String user_id) {
			super();
			this.username = username;
			this.password = password;
			this.user_id = user_id;
		}


		public String getCreated_user() {
			return created_user;
		}


		public void setCreated_user(String created_user) {
			this.created_user = created_user;
		}


		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getCreate_time() {
			return create_time;
		}
		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public LocalDateTime getLast_login() {
			return last_login;
		}


		public void setLast_login(LocalDateTime last_login) {
			this.last_login = last_login;
		}
		
		
}
