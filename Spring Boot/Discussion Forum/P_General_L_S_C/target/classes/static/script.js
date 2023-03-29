console.log("hello js");
console.log("hello js  5");
console.log("hello js 88");
show();
function hideloader(){
	console.log("hide everything");
}


//displaying funtion through array json
async function show() {
	const url = "http://localhost:8086/message/getallmessage";
	console.log(url);
	const response = await fetch(url);
	
	
	var data = await response.json();
	console.log(data);
	
	
	if (response) {
		hideloader();
	}
	
	//let tab=``;
	
	/*
	let tab=
	    `
		<div>
             <div>
                  <h1 id="M_">This is dummy blog</h1>
                  <button id="BL_"> like </button>
                  <p id="L_">Like 5</p>
                  <button id="BC_"> comment </button>
                  <input type="text" />
             </div>
             <div id="C_">
                  <p> comment 1</p>
                  <p> comment 2</p>
                  <p> comment 3</p>
             </div>
        </div>
		
		`;
	
	*/
	
	/*
	
	let tab =
		`<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Addr</th>
		</tr>`;
		
		*/
		
		/*
		
		for (let i = 0; i < data.length; i++) {
			
			let tab1=`
			<div>
             <div>
                  <h1>{data[i].message}</h1>
                  <button> like </button>
                  <p>Like {data[i].like_count}</p>
                  <button> comment </button>
                  <input type="text" />
             </div>
			`;
			
			let tab2=`
			</div>
			`;
			tab+=tab1+tab2;
			

	}	
	
	*/
		
		
/*		

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
	
	
	*/

	//document.getElementById("tmp").innerHTML = tab;
}