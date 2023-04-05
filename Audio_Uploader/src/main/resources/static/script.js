//csv upload section
async function my_save(){
	//var input = document.querySelector('input[type="file"]')
	var input=document.getElementById("img_play");
    var data = new FormData()
    data.append('image', input.files[0])

 await fetch("http://localhost:8099/audio/upload", {
  method: 'POST',
  body: data
})
}

//csv download section
async function my_download(){
try {
        
        const res = await fetch("http://localhost:8099/audio/download", {
            method: 'get',
            headers: {
                'content-type': 'text/csv;charset=UTF-8',
                //'Authorization': //in case you need authorisation
            }
        });

        if (res.status === 200) {

            const data = await res.text();
            console.log(data);
		  var player = document.getElementById('myAudio');
                            player.src = "data:audio/mp3;base64,"+data;
                            player.load();
                            player.play();

        } else {
            console.log(`Error code ${res.status}`);
        }
    } catch (err) {
        console.log(err)
    }

}