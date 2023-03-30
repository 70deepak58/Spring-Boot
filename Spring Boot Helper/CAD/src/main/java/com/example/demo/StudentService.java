package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentService {
	@Autowired
	private StudentC sc;
	public List<Student> findsAll(){
		return(List<Student>) sc.findAll();
	}
	public String deleteUserById(int id) {
		sc.deleteById(id);
		return "Deleted";
	}
	public String addUser(Student s) {
		sc.save(s);
		return "Add S";
	}
	public Student findOne(int id) {
		Student ans= sc.findById(id).orElse(null);
		sc.deleteById(id);
		return ans;
	}
	public Student updateStudent(Student s,int id) {
		Student ans= sc.findById(id).orElse(null);
	    ans.setName(s.getName());
	    ans.setAddr(s.getAddr());
	    sc.save(ans);
		return ans;
	}
	
}
