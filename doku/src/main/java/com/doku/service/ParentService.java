package com.doku.service;

import java.util.List;

import com.doku.model.Users.Parent;
import com.doku.repository.ParentRepository;

public class ParentService {
	private ParentRepository parentRepository;
	
	public ParentService() {
		parentRepository = new ParentRepository();
	}
	
	public void addParent(Parent parent) {
		parentRepository.addParent(parent);
	}
	
	public Parent getParentById(int id) {
		return parentRepository.getParentById(id);
	}
	
	public Parent authenticateParent(String email, String password) {
		return parentRepository.authenticateParent(email, password);
	}
	
	
	public List<Parent> getAllParents(){
		return parentRepository.getAllParent();
	}
	
	public void updateParent(Parent parent) {
		parentRepository.updateParent(parent);
	}
	
	public void deleteParent(int id) {
		parentRepository.deleteParent(id);
	}
	
}
