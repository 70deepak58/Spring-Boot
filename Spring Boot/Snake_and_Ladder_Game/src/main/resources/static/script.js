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
		                op_score=json.score;
		                op_turn=json.turn;
		                p2_score=op_score;
		                snake_bite(2,p2_score);
                        ladder_up(2,p2_score);
		                op_score=p2_score;
		                
		                
		                
		                document.getElementById("player2score").innerHTML=op_score;
	                    document.getElementById("player2turn").innerHTML=op_turn;
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
		
		//change from
		p1_score=my_score;
		snake_bite(1,p1_score);
        ladder_up(1,p1_score);
		
		my_score=p1_score;
		
		//change to
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

//my my my logic for snake
/*
shgdj hwdvmn
dwe dkjwd
e kfjw
j deq
'ej fw'eef ;
fieoife
ifmiefpq
*/
var img;
var no;
var p1_score=0;
var p2_score=0;
var turn=0;
var bite=0;
var up=0;
var Goti_P1_Top;
var Goti_P1_Left;
var Goti_P2_Top;
var Goti_P2_Left;

//image change background
var snake_no=1;
var Stop_snake_img_chg=0;

//snake function
function snake_bite(flag,l){
    if(flag==1){
        //player 1 bite


        //snake no=1
        if(snake_no==1){
    if(l==17){
       p1_score-=10;
       bite=1; 
    }
    if(l==54){
        p1_score-=20;
        bite=1; 
     }
     if(l==62){
        p1_score-=43; 
        bite=1;
     }
     if(l==64){
        p1_score-=4;
        bite=1; 
     }
     if(l==87){
        p1_score-=51;
        bite=1;
     }
     if(l==93){
        p1_score-=20; 
        bite=1;
     }
     if(l==95){
        p1_score-=20;
        bite=1;
     }
     if(l==98){
        p1_score-=19; 
        bite=1;
     }}
     //console.log(p1_score);
     Goti_P1_Top=900-90*Math.floor(p1_score/10)-90;
     Goti_P1_Left=90*Math.floor(p1_score%10)-90;
     if(((Math.floor(p1_score/10))%2!=0)&&(p1_score/10)!=0){
        Goti_P1_Left=900-(90*Math.floor(p1_score%10));
        //Goti_P1_Left=0;
        console.log("odd"+p1_score/10);
                }
                //bug 20 40 60
     if((Math.floor((p1_score/10)%2==0))&&(p1_score%10==0)){
         Goti_P1_Left=0;
         Goti_P1_Top=900-90*Math.floor(p1_score/10);
       // prompt("bug here");
       console.log("bug there");

    }
    //bug2 10 20 30
    if((Math.floor((p1_score/10)%2!=0))&&(p1_score%10==0)){
        Goti_P1_Left=800;
        Goti_P1_Top=900-90*Math.floor(p1_score/10);
      // prompt("bug here");
      console.log("bug there");

   }
     document.getElementById("Goti_P1").style.top=Goti_P1_Top+"px";
     document.getElementById("Goti_P1").style.left=Goti_P1_Left+"px";
     console.log(Goti_P1_Top);
     console.log(Goti_P1_Left);
     
if(bite==1){
    //window.prompt("kat liya p1 ko");
    console.log("kat liya");
}
     bite=0;
    
}
else{
    //player 2 bite

    //snake no=1
    if(snake_no==1){
    if(l==17){
        p2_score-=10; 
        bite=1;
     }
     if(l==54){
         p2_score-=20; 
         bite=1;
      }
      if(l==62){
         p2_score-=43; 
         bite=1;
      }
      if(l==64){
         p2_score-=4; 
         bite=1;
      }
      if(l==87){
         p2_score-=51; 
         bite=1;
      }
      if(l==93){
         p2_score-=20; 
         bite=1;
      }
      if(l==95){
         p2_score-=20; 
         bite=1;
      }
      if(l==98){
         p2_score-=19; 
         bite=1;
      }}

      Goti_P2_Top=900-90*Math.floor(p2_score/10)-90;
      Goti_P2_Left=90*Math.floor(p2_score%10)-90;
      if(((Math.floor(p2_score/10))%2!=0)&&(p2_score/10)!=0){
         Goti_P2_Left=900-(90*Math.floor(p2_score%10));
         //Goti_P1_Left=0;
         console.log("odd"+p2_score/10);
                 }
                            //bug 20 40 60
     if((Math.floor((p2_score/10)%2==0))&&(p2_score%10==0)){
        Goti_P2_Left=0;
        Goti_P2_Top=900-90*Math.floor(p2_score/10);
      // prompt("bug here");
      console.log("bug there");

   }
      //bug2 10 20 30
      if((Math.floor((p2_score/10)%2!=0))&&(p2_score%10==0)){
        Goti_P2_Left=800;
        Goti_P2_Top=900-90*Math.floor(p2_score/10);
      // prompt("bug here");
      console.log("bug there");

   }
      document.getElementById("Goti_P2").style.top=Goti_P2_Top+"px";
      document.getElementById("Goti_P2").style.left=Goti_P2_Left+"px";
      
      document.getElementById("P2").innerHTML="P2 SCORE:"+p2_score;
      if(bite==1){
         // window.prompt("kat liya p2 ko");
         console.log("kat liya p2 ko");
      }
      //bug here
      if((Math.floor((p1_score/10)%2==0))&&(p1_score%10==0)){
       // prompt("bug here");
       console.log("bug there");

    }
      bite=0;
      //console.log(p2_score);
}

}



function ladder_up(flag2,m){
    if(flag2==1){
        //player 1 up

        //snake no =1
        if(snake_no==1){
        if(m==4){
            p1_score+=10;
            up=1; 
         }
         if(m==9){
             p1_score+=22;
             up=1; 
          }
          if(m==21){
             p1_score+=21; 
             up=1;
          }
          if(m==28){
             p1_score+=56;
             up=1; 
          }
          if(m==51){
             p1_score+=16;
             up=1;
          }
          if(m==72){
             p1_score+=19; 
             up=1;
          }
          if(m==80){
             p1_score+=19; 
             up=1;
          }}
                 
          Goti_P1_Top=900-90*Math.floor(p1_score/10)-90;
          Goti_P1_Left=90*Math.floor(p1_score%10)-90;
          if(((Math.floor(p1_score/10))%2!=0)&&(p1_score/10)!=0){
Goti_P1_Left=900-(90*Math.floor(p1_score%10));
console.log("odd"+p1_score/10);
//Goti_P1_Left=0;
        }

         //bug here 20 40 60
         if((Math.floor((p1_score/10)%2==0))&&(p1_score%10==0)){
             Goti_P1_Left=0;
             Goti_P1_Top=900-90*Math.floor(p1_score/10);
           // prompt("bug here");
           console.log("bug there");
   
        }

        //bug2 10 20 30
        //bug2 10 20 30
    if((Math.floor((p1_score/10)%2!=0))&&(p1_score%10==0)){
        Goti_P1_Left=800;
        Goti_P1_Top=900-90*Math.floor(p1_score/10);
      // prompt("bug here");
      console.log("bug there");

   }
          document.getElementById("Goti_P1").style.top=Goti_P1_Top+"px";
          document.getElementById("Goti_P1").style.left=Goti_P1_Left+"px";
          if(up==1){
             // window.prompt("ladder p1");
             console.log("laddaer P1");
          }
          up=0;
    }
    else{
        //player 2 up

        //snake no =1
        if(snake_no==1){
        if(m==4){
            p2_score+=10;
            up=1; 
         }
         if(m==9){
             p2_score+=22;
             up=1; 
          }
          if(m==21){
             p2_score+=21; 
             up=1;
          }
          if(m==28){
             p2_score+=56;
             up=1; 
          }
          if(m==51){
             p2_score+=16;
             up=1;
          }
          if(m==72){
             p2_score+=19; 
             up=1;
          }
          if(m==80){
             p2_score+=19; 
             up=1;
          }} 
      Goti_P2_Top=900-90*Math.floor(p2_score/10)-90;
      Goti_P2_Left=90*Math.floor(p2_score%10)-90;
      if(((Math.floor(p2_score/10))%2!=0)&&(p2_score/10)!=0){
         Goti_P2_Left=900-(90*Math.floor(p2_score%10));
         //Goti_P1_Left=0;
         console.log("odd"+p2_score/10);
                 }

                                         //bug 20 40 60
     if((Math.floor((p2_score/10)%2==0))&&(p2_score%10==0)){
        Goti_P2_Left=0;
        Goti_P2_Top=900-90*Math.floor(p2_score/10);
      // prompt("bug here");
      console.log("bug there");

   }
       //bug2 10 20 30
       if((Math.floor((p2_score/10)%2!=0))&&(p2_score%10==0)){
        Goti_P2_Left=800;
        Goti_P2_Top=900-90*Math.floor(p2_score/10);
      // prompt("bug here");
      console.log("bug there");

   }
      document.getElementById("Goti_P2").style.top=Goti_P2_Top+"px";
      document.getElementById("Goti_P2").style.left=Goti_P2_Left+"px";
          document.getElementById("P2").innerHTML="P2 SCORE:"+p2_score;
          //bug here
          if((Math.floor((p1_score/10)%2==0))&&(p1_score%10==0)){
           // prompt("bug here");
           console.log("bug there");
   
        }
          if(up==1){
             // window.prompt("ladder p2");
             console.log("ladder p2");
          }
          up=0;
    }

}



function win_check(){
   if(p1_score>=100){
      window.alert("P1 won");
   }
   if(p2_score>=100){
      window.alert("P2 won");
   }
}


/*
function dice_roll(){
	no=1+Math.floor(6*Math.random());
    if(turn%2==0){
        p1_score+=no;
        snake_bite(1,p1_score);
        ladder_up(1,p1_score);
        win_check();
    }
    else{
        p2_score+=no;
        snake_bite(2,p2_score);
        ladder_up(2,p2_score);
        win_check();
    }
    turn++;
}

*/

















