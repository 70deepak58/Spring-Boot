show();
function hideloader() {
	console.log("write hide logic");
}

//This is show section
async function show(){
	const url = "http://localhost:8086/message/getallmessage";
	console.log(url);
	const response = await fetch(url);
	var data = await response.json();
	console.log(data);
	if (response) {
		hideloader();
	}
	
	let tab=``;	
	for (let i = 0; i < data.length; i++) {
			//B--button     D--Delete   E--Edit   C--Comment M==P--Message==Post  L--Like 
			let tab1=`
			<div class="cas">
             <div>
                  <h1 id="MB_${data[i].id}">${data[i].id}. ${data[i].message}</h1> <button id="BEB_${data[i].id}" onclick="editblog(this.id)">edit</button><br/>
                  <button id="BL_${data[i].id}" onclick="like(this.id)"> like </button>
                  <p id="L_${data[i].id}">Like ${data[i].like_count}</p>
                  <button id="BC_${data[i].id}" onclick="addcomment(this.id)"> comment </button>
                  <input id="I_${data[i].id}" type="text" />
                  <button id="BDC_${data[i].id}" onclick="deletecomment(this.id)"> delete </button>
                  <button id="BEC_${data[i].id}" onclick="editcomment(this.id)"> edit </button>
             </div>
			`;
			
			
		    let tabf=`<div id="C_${data[i].id}">`;
	        for (let key in data[i].comment_map) {
                 if (data[i].comment_map.hasOwnProperty(key)) {
	                 tabf+=`<p class="cac" id="CI_${key}">${key}. ${(data[i].comment_map)[key]}</p>`;
                     console.log(key, (data[i].comment_map)[key]);
                  }
            }
			let tab2=`
			</div></div>
			`;
			tab+=tab1+tabf+tab2;
	}	
	document.getElementById("tmp").innerHTML = tab;
}

//This is like section
async function like(x){
	console.log(x);
	let id=Math.floor(Math.random() * 100000);
	let mid=Number(x.substring(3, x.length));
	await fetch("http://localhost:8089/general/all", {
		method: "POST",
		mode: 'no-cors',
		body: JSON.stringify({
        id: id,
       c_flag:0,
        l_flag:1,
        message:"nope",
        message_id: mid
    }),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
	await show();
}

//This is addcomment section
async function addcomment(x){
	console.log(x);
	let id=Math.floor(Math.random() * 100000);
	let mid=Number(x.substring(3, x.length));
	let msg=document.getElementById("I_"+mid).value;
	console.log(x+msg);
	
	if(msg!=""){
		await fetch("http://localhost:8089/general/all", {
		method: "POST",
		mode: 'no-cors',
		body: JSON.stringify({
        id: id,
        c_flag:1,
        l_flag:0,
        message:msg,
        message_id: mid
        }),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	})
	await show();	
	}
}

//This is deletecomment section
async function deletecomment(x){
	console.log(x);
	let mid=Number(x.substring(4, x.length));
	let msg=document.getElementById("I_"+mid).value;
	console.log(x+msg);
	
	if(msg!=""){
		await fetch("http://localhost:8086/message/deletecomment", {
		method: "POST",
		body: JSON.stringify({
        id: mid,
        message: "delete comment",
        comment_count: msg,
        like_count: 0,
        user_id: 0,
        }),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	    })
	    await show();
	    document.getElementById("pos").value='';
	}
}

//This is editcomment section
async function editcomment(x){
	console.log(x);
	let mid=Number(x.substring(4, x.length));
	let msg=document.getElementById("I_"+mid).value;
	const myArray=msg.split("@");
	console.log(myArray[0]);
	console.log("shdhsfdh");
	console.log(myArray[1]);
	console.log("edit comment section")
	
	if(myArray[1]!=""){
		await fetch("http://localhost:8086/message/editcomment", {
		method: "POST",
		body: JSON.stringify({
        id: mid,
        message: myArray[1],
        comment_count: myArray[0],
        like_count: 0,
        user_id: 0,
        }),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	    })
	    await show();
	    document.getElementById("pos").value='';
	}
}

//This is addblog section
async function addblog(){
	let id=Math.floor(Math.random() * 100000);
	let msg=document.getElementById("pos").value;
	if(msg!=""){
		await fetch("http://localhost:8086/message/addmessage", {
		method: "POST",
		body: JSON.stringify({
        id: id,
        message: msg,
        comment_count: 0,
        like_count: 0,
        user_id: 0,
        }),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	    })
	    await show();
	    document.getElementById("pos").value='';
	}
}

//This is deleteblog section
async function deleteblog(){
	let id=document.getElementById("pos").value;
	if(id!=""){
		id=Number(id);
		await fetch("http://localhost:8086/message/deletemessage", {
		method: "POST",
		body: JSON.stringify({
        id: id,
        message: "delete",
        comment_count: 0,
        like_count: 0,
        user_id: 0,
        }),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	    })
	    await show();
	    document.getElementById("pos").value='';
	}
}

//This is edit blog section
async function editblog(x){
	console.log(x);
	//let id=Math.floor(Math.random() * 100000);
	let id=Number(x.substring(4, x.length));
	let msg=document.getElementById("I_"+id).value;
	console.log(x+msg);
	
	if(msg!=""){
		await fetch("http://localhost:8086/message/editmessage", {
		method: "POST",
		body: JSON.stringify({
        id: id,
        message: msg,
        comment_count: 0,
        like_count: 0,
        user_id: 0,
        }),
		headers: {
			"Content-type": "application/json; charset=UTF-8"
		}
	    })
	    await show();
	}
}
