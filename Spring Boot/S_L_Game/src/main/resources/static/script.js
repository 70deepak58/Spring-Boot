///our logic stats here
function hideloader(){
	console.log("some error");
}
async function show(){
	
}

var my_id, my_name, my_pass, my_status, my_score, my_turn=1,my_msg="null";
var op_id, op_name, op_pass, op_status, op_score, op_turn,op_msg="null";
//this is login adapter
//function dlg(){
//	hideepak();
//}

//i tried login function but not worked
async function hideepak(){
	var u1=document.getElementById("u1").value;
	var p1=document.getElementById("p1").value;
	my_id=-67;
    my_name=u1;
	my_pass=p1;
	my_status=-68;
    my_score=-90;
    my_turn=-1;
	my_msg="initial dummy";
	await fetch("http://localhost:8085/game/lg", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: u1,
			pass: p1,
			status:my_status,
			score:my_score,
			turn:my_turn,
			msg:my_msg

		}),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
		.then((response) => response.json())
		.then((json) =>{console.log(json);
		               // document.getElementById("p2s").innerHTML=json.score;
	                    //document.getElementById("p2t").innerHTML=json.turn;
	                    //op_turn=json.turn;
	                    //console.log(json.turn);
	         my_id=json.id;
			 my_name=json.name;
			 my_pass=json.pass;
			my_status=json.status;
			my_score=0;
			my_turn=1;
			my_msg="by login";
			console.log(my_id, my_name, my_pass, my_status, my_score, my_turn, my_msg);
	                    
		});
	
	
	
}



async function register() {
	my_id=Number(document.getElementById("r1").value);
	my_name=document.getElementById("r2").value;
	my_pass=document.getElementById("r3").value;
	my_status=Number(document.getElementById("r4").value);
	my_score=Number(document.getElementById("r5").value);
	my_turn=Number(document.getElementById("r6").value);
	my_msg=document.getElementById("r7").value;
console.log(my_id);


	await fetch("http://localhost:8085/game/create", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			pass: my_pass,
			status:my_status,
			score:my_score,
			turn:my_turn,
			msg:my_msg

		}),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
		.then((response) => response.json())
		.then((json) => console.log(json));


	//for showing detail
	 show();
}




//full game
async function play(){
	//getting opponent status
	await fetch("http://localhost:8085/game/gs", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			pass: my_pass,
			status:my_status,
			score:my_score,
			turn:my_turn,
			msg:my_msg

		}),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
		.then((response) => response.json())
		.then((json) =>{console.log(json);
		                document.getElementById("p2s").innerHTML=json.score;
	                    document.getElementById("p2t").innerHTML=json.turn;
	                    op_turn=json.turn;
	                    console.log(json.turn);
		});


	//for showing detail
	
	
	//this is to cheack for turn
	
	if(op_turn==0){
		my_turn=0;
		op_turn=1;
		var j=Math.floor(Math.random() * 7);
		document.getElementById("v").innerHTML=j;
		my_score=my_score+j;
		document.getElementById("p1s").innerHTML=my_score;
	    document.getElementById("p1t").innerHTML=my_turn;
	    document.getElementById("p2t").innerHTML=op_turn;
		await fetch("http://localhost:8085/game/play", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			pass: my_pass,
			status:my_status,
			score:my_score,
			turn:my_turn,
			msg:my_msg
		}),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
		.then((response) => response.json())
		.then((json) => console.log(json));
		
		
		
		
	}
	else{
		console.log("player 2 turn");
	}
	
	
	
		
		
		
}

