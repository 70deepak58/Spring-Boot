package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/general")
public class GeneralMController {
	@Autowired
	private FeignComment fc;
	@Autowired
	private FeignLike fl;
	@PostMapping("/all")
	public String LAM(@RequestBody String lst) throws JSONException {
		try {
			
			    GeneralM gm=new ObjectMapper().readValue(lst, GeneralM.class);
			    JSONObject jobj=new JSONObject();

				jobj.put("id", gm.getMessage_id());
				jobj.put("message", gm.getMessage());
				jobj.put("comment_count", gm.getId());
				jobj.put("like_count", gm.getId());
				jobj.put("user_id", 0);
				if(gm.getC_flag()==1 && gm.getL_flag()==1) {
					fc.addingCommentOnMessage(jobj.toString());
					fl.addLikeAMessage(jobj.toString());
					return "comment done "+"#"+"like done";
				}
				else if(gm.getC_flag()==1) {
					fc.addingCommentOnMessage(jobj.toString());
					return "comment done ";
				}
				else if(gm.getL_flag()==1) {
					fl.addLikeAMessage(jobj.toString());
					return "like done";
				}
				//return fa.addLikeAMessage(jobj.toString());
				return "no ops";
			
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return "";
		}

	}

