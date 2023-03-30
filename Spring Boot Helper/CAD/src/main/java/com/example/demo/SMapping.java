package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dee")
public class SMapping {
	@Autowired
	private StudentService ssc;
	@GetMapping("/g")
	public List<Student> gettingALLUsr(){
		List<Student> it=ssc.findsAll();
		return it;
	}
	@PostMapping("/a")
	public Student addingUsr(@RequestBody Student lst){
		 ssc.addUser(lst);
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
	
	

}
