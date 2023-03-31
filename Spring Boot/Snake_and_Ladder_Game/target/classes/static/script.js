///our logic stats here
var my_id, my_name, my_password, my_tag_id, my_score, my_turn=1,my_message="null";
var op_id, op_name, op_password, op_tag_id, op_score, op_turn,op_message="null";
var myI;
function hideloader(){
	console.log("some error");
}
async function show(){
	
}

//Player register section
async function playerRegister() {
	console.log("register");
	my_id=Number(document.getElementById("r1").value);
	my_name=document.getElementById("r2").value;
	my_password=document.getElementById("r3").value;
	my_tag_id=Number(document.getElementById("r4").value);
	my_score=0;
	op_score=0;
	if(my_id>my_tag_id){
		my_turn=1;
		op_turn=0;
	}
	else{
		my_turn=0;
		op_turn=1;
	}
	my_message="By Register";
	console.log(my_id, my_name, my_password, my_tag_id, my_score, my_turn, my_message);

	await fetch("http://localhost:8090/game/register", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			password: my_password,
			tag_id:my_tag_id,
			score:my_score,
			turn:my_turn,
			message:my_message

		}),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
		.then((response) => response.json())
		.then((json) =>{ console.log(json);
		
		    my_id=json.id;
			my_name=json.name;
			my_password=json.password;
			my_tag_id=json.tag_id;
			my_score=json.score;
			my_turn=json.turn;
			my_message=json.message;
			console.log(my_id, my_name, my_password, my_tag_id, my_score, my_turn, my_message);
		
		
		});

    document.getElementById("player2score").innerHTML=op_score;
    document.getElementById("player1score").innerHTML=my_score;
    document.getElementById("player2turn").innerHTML=op_turn;
    document.getElementById("player1turn").innerHTML=my_turn;
	//for showing detail
	 show();
}


//Player login section
async function playerLogin(){
	console.log("logging");	
	my_id=Number(document.getElementById("l1").value);
	my_name="dummy";
	my_password=document.getElementById("l2").value;
	my_tag_id=Number(document.getElementById("l3").value);
	my_score=0;
	op_score=0;
	if(my_id>my_tag_id){
		my_turn=1;
		op_turn=0;
	}
	else{
		my_turn=0;
		op_turn=1;
	}
	my_message="By Login";
	console.log(my_id);


	await fetch("http://localhost:8090/game/login", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			password: my_password,
			tag_id:my_tag_id,
			score:my_score,
			turn:my_turn,
			message:my_message

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
			my_password=json.password;
			my_tag_id=json.tag_id;
			my_score=json.score;
			my_turn=json.turn;
			my_message=json.message;
			console.log(my_id, my_name, my_password, my_tag_id, my_score, my_turn, my_message);                    
		});
		
    document.getElementById("player2score").innerHTML=op_score;
    document.getElementById("player1score").innerHTML=my_score;
    document.getElementById("player2turn").innerHTML=op_turn;
    document.getElementById("player1turn").innerHTML=my_turn;
		
		
}

//Continuous update
async function continuosUpdate(){
	console.log("hi");
	//Opposition update
	await fetch("http://localhost:8090/game/opuser", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			password: my_password,
			tag_id:my_tag_id,
			score:my_score,
			turn:my_turn,
			message:my_message

		}),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
		.then((response) => response.json())
		.then((json) =>{console.log(json);
		                //op_turn=json.turn;
		                document.getElementById("player2score").innerHTML=json.score;
	                    document.getElementById("player2turn").innerHTML=json.turn;
	                    op_turn=json.turn;
	                    console.log(json.turn);
		});
		
		//My update section
		await fetch("http://localhost:8090/game/user", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			password: my_password,
			tag_id:my_tag_id,
			score:my_score,
			turn:my_turn,
			message:my_message

		}),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
		.then((response) => response.json())
		.then((json) =>{console.log(json);
		                //my_turn=json.turn;
		                document.getElementById("player1score").innerHTML=json.score;
	                    document.getElementById("player1turn").innerHTML=json.turn;
	                    my_turn=json.turn;
	                    console.log(json.turn);
		});
		
		//False section
		if(op_turn==0){
			my_turn=1;
			document.getElementById("player1turn").innerHTML=my_turn;
		}
		if(my_turn==1){
			clearInterval(myI);
		}
}

//Player throw dice and play section
async function play(){
	//getting opponent status
	await fetch("http://localhost:8090/game/opuser", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			password: my_password,
			tag_id:my_tag_id,
			score:my_score,
			turn:my_turn,
			message:my_message

		}),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
		.then((response) => response.json())
		.then((json) =>{console.log(json);
		                op_turn=json.turn;
		                document.getElementById("player2score").innerHTML=json.score;
	                    document.getElementById("player2turn").innerHTML=json.turn;
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
		document.getElementById("player1score").innerHTML=my_score;
	    document.getElementById("player1turn").innerHTML=my_turn;
	    document.getElementById("player2turn").innerHTML=op_turn;
		await fetch("http://localhost:8090/game/play", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			password: my_password,
			tag_id:my_tag_id,
			score:my_score,
			turn:my_turn,
			message:my_message
		}),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
		.then((response) => response.json())
		.then((json) => console.log(json));
		myI=setInterval(continuosUpdate,1000);
	
	}
	else{
		console.log("player 2 turn");
	}		
}

//Player reset section not completed yet dummy
async function playerReset(){
	console.log("logging");	
	my_id=Number(document.getElementById("l1").value);
	my_name="dummy";
	my_password=document.getElementById("l2").value;
	my_tag_id=Number(document.getElementById("l3").value);
	my_score=0;
	op_score=0;
	if(my_id>my_tag_id){
		my_turn=1;
		op_turn=0;
	}
	else{
		my_turn=0;
		op_turn=1;
	}
	my_message="By Login";
	console.log(my_id);


	await fetch("http://localhost:8098/game/reset", {
		method: "POST",
		body: JSON.stringify({
			id: my_id,
			name: my_name,
			password: my_password,
			tag_id:my_tag_id,
			score:my_score,
			turn:my_turn,
			message:my_message

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
			my_password=json.password;
			my_tag_id=json.tag_id;
			my_score=json.score;
			my_turn=json.turn;
			my_message=json.message;
			console.log(my_id, my_name, my_password, my_tag_id, my_score, my_turn, my_message);                    
		});
		
    document.getElementById("player2score").innerHTML=op_score;
    document.getElementById("player1score").innerHTML=my_score;
    document.getElementById("player2turn").innerHTML=op_turn;
    document.getElementById("player1turn").innerHTML=my_turn;
		
		
}











