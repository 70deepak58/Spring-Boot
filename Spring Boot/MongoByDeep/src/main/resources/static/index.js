show();

function hideloader() {
	console.log("write hide logic");
}



//displaying funtion through array json
async function show() {
	const url = "http://localhost:8080/dee/g";
	console.log(url);
	const response = await fetch(url);
	var data = await response.json();
	console.log(data);
	if (response) {
		hideloader();
	}
	
	
	
	let tab =
		`<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Addr</th>
		</tr>`;

	// Loop to access all rows
	for (let i = 0; i < data.length; i++) {
		tab += `<tr>
		<td>${data[i].id} </td>
	<td>${data[i].name} </td>
	<td>${data[i].addr}</td>		
</tr>`;
	}
	// Setting innerHTML as tab variable

	console.log(tab);

	document.getElementById("tmp").innerHTML = tab;
}



//This is for adding setions
async function go_add() {
	console.log("inside go");
	let yy = document.getElementById("aa1").value;
	let x = document.getElementById("a1").value;
	let y = document.getElementById("a2").value;
	console.log(x);
	console.log(y);



	await fetch("http://localhost:8080/dee/a", {
		method: "POST",
		body: JSON.stringify({
			id: yy,
			name: x,
			addr: y

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




//This is for delete section 
async function go_del() {
	console.log("inside go");
	let x = document.getElementById("d1").value;
	console.log(x);
    // Instantiating new EasyHTTP class
    
   const url1= "http://localhost:8080/dee/d/"+x;
   console.log(url1);
   await fetch(url1, {
            method: 'DELETE',
            headers: {
                'Content-type': 'application/json'
            }
        });

	//for showing detail
	
	 show();


}



//This is for update section 
async function go_update() {
	console.log("inside go");
	let x = document.getElementById("u1").value;
	let y = document.getElementById("u2").value;
	let z = document.getElementById("u3").value;
	console.log(x);
    // Instantiating new EasyHTTP class
    
   const url1= "http://localhost:8080/dee/u/"+x;
   await fetch(url1, {
  method: 'PUT',
  body: JSON.stringify({
    name: y,
    addr:z
  }),
  headers: {
    'Content-type': 'application/json; charset=UTF-8',
  },
})
  .then((response) => response.json())
  .then((json) => console.log(json));
  
	//for showing detail
	show();
	


}