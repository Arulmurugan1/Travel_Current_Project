package com.web.objects;

import java.time.LocalDateTime;

public class AccessLog 
{
    public String user_id, username, role, platform, accept_language, protocol, url, Remote_host, Remote_address, Local_address, Local_name, Local_lang, logged_time;
    public LocalDateTime access_time ;
    
    
    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAccept_language() {
        return this.accept_language;
    }

    public void setAccept_language(String accept_language) {
        this.accept_language = accept_language;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }



    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemote_host() {
        return this.Remote_host;
    }

    public void setRemote_host(String remote_host) {
        this.Remote_host = remote_host;
    }

    public String getRemote_address() {
        return this.Remote_address;
    }

    public void setRemote_address(String remote_address) {
        this.Remote_address = remote_address;
    }

    public String getLocal_address() {
        return this.Local_address;
    }

    public void setLocal_address(String local_address) {
        this.Local_address = local_address;
    }

    public String getLocal_name() {
        return this.Local_name;
    }

    public void setLocal_name(String local_name) {
        this.Local_name = local_name;
    }

    public String getLocal_lang() {
        return this.Local_lang;
    }

    public void setLocal_lang(String local_lang) {
        this.Local_lang = local_lang;
    }

    public LocalDateTime getAccess_time() {
        return this.access_time;
    }

    public void setAccess_time(LocalDateTime access_time) {
        this.access_time = access_time;
    }

    public String getLogged_time() {
        return this.logged_time;
    }

    public void setLogged_time(String logged_time) {
        this.logged_time = logged_time;
    }

    @Override
    public String toString() {
        return "AccessLog [user_id=" + this.user_id + ", username=" + this.username + ", role=" + this.role
                + ", platform=" + this.platform + ", accept_language=" + this.accept_language + ", protocol="
                + this.protocol + ", url=" + this.url + ", Remote_host=" + this.Remote_host + ", Remote_address="
                + this.Remote_address + ", Local_address=" + this.Local_address + ", Local_name=" + this.Local_name
                + ", Local_lang=" + this.Local_lang + ", logged_time=" + this.logged_time + ", access_time="
                + this.access_time + "]";
    }
}
