package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Profile;



public class ProfileService {
	
	public List<Profile> profileList= new ArrayList<>();
	
	//Contructor de pruba, no es necesario ahorita esto solo es de prueba
	//cuando tengamos ya listo la conexion a la base de datos hay que cambiar esto
	public ProfileService() {
		profileList.add(new Profile("Diego","124"));
		profileList.add(new Profile("Mike","123456"));
	}
	
	public boolean checkIfIsCorrect(Profile profile) 
	{
		boolean flag=false;
		for(Profile profiles: profileList) 
		{
			if(profile.equalsLogin(profiles)) 
			{
				flag= true;
			}
		}
		return flag;
	}
	
	public void addProfile(Profile profile) 
	{
		boolean flag= false;
		for(Profile profiles: profileList) 
		{
			if(profile.equals(profiles)) 
			{
				flag= true;
			}
		}
		if(!flag) {profileList.add(profile);}
	}
	
	public Profile getProfile(int index) 
	{
		return profileList.get(index);
	}
}
