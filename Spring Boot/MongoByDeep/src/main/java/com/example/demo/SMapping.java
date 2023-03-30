package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/dee")

public class SMapping {
	
	@Autowired
	private StudCont ssc;
	@GetMapping("/g")
	public List<Student> gettingALLUsr(){
		List<Student> it=ssc.findsAll();
		return it;
	}
//	@PostMapping("/a")
//	public Student addingUsr(@RequestBody Student lst){
//		Student std=new Student();
//		 ssc.addUser(lst);
//		 return lst;
//	}
	@PostMapping("/a")
	public String addingUsr(@RequestBody String lst){
		//Item itemWithOwner = new ObjectMapper().readValue(json, Item.class);
		//JSONObject jobj= new JSONObject();
		//jsonObject= new JSONObject(content );
		//Student std=new Student();
		//int id=jobj.
		//std.setId(7);
		// ssc.addUser(std);
		try {
			Student std= new ObjectMapper().readValue(lst,Student.class);
			ssc.addUser(std);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return lst;
	}
	@DeleteMapping("/d/{id}")
	public Student deletingUsr(@PathVariable Integer id){
		Student dd=ssc.findOne(id);
		 return dd;
	}
	
	@PutMapping("/u/{id}")
	public Student updateStudent(@RequestBody Student student, @PathVariable("id") int id) {
		ssc.updateStudent(student, id);
		return student;
		
	}
	@PostMapping("/gn")
	public List<Student>  gettingUsr(@RequestBody Student s){
		List<Student> ans=new ArrayList<Student>();
		List<Student> f=ssc.findsAll();
		String yy=s.name;
		for(int i=0;i<f.size();i++) {
			String yyy=f.get(i).name;
			if(yy.equals(yyy)) {
				ans.add(f.get(i));				
			}
		}
		return ans;
	}
}
