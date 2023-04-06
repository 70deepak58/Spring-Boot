//csv upload section
async function my_save(){
	//var input = document.querySelector('input[type="file"]')
	var input=document.getElementById("img_play");
    var data = new FormData()
    data.append('image', input.files[0])

 await fetch("http://localhost:8081/img/upload", {
  method: 'POST',
  body: data
})
}

//csv download section
async function my_download(){
try {
        
        const res = await fetch("http://localhost:8081/img/download", {
            method: 'get',
            headers: {
                'content-type': 'text/csv;charset=UTF-8',
                //'Authorization': //in case you need authorisation
            }
        });

        if (res.status === 200) {

            const data = await res.text();
            console.log(data);
            //c1
		    document.getElementById("di").src="data:image;base64," + data;	   
		   //This is logic failed to apply for music and video hee
		   /*
		   	<audio id="msc">
                <source src="a.wav" > 
            </audio>
        		   document.getElementById("msc").src="data:audio/wav;charset=utf-8;base64,Zm9vIGJhcg==" + data;
		   var x=document.getElementById("msc");
           x.play();
		   */   

		   

        } else {
            console.log(`Error code ${res.status}`);
        }
    } catch (err) {
        console.log(err)
    }

}