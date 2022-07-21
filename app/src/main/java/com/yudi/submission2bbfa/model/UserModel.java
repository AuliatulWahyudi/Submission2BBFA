package com.yudi.submission2bbfa.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class UserModel {

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("id")
	private int id;

	@SerializedName("login")
	private String login;

	@SerializedName("gravatar_id")
	private String gravatarId;

	@SerializedName("url")
	private String url;

	public void setAvatarUrl(String avatarUrl){
		this.avatarUrl = avatarUrl;
	}

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLogin(String login){
		this.login = login;
	}

	public String getLogin(){
		return login;
	}

	public void setGravatarId(String gravatarId){
		this.gravatarId = gravatarId;
	}

	public String getGravatarId(){
		return gravatarId;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
}