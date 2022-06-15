let test = sessionStorage.getItem('user')
console.log(test)
let inputText = document.querySelector('#message')
console.log(inputText.value)
let shiftPress = false
let enterPress = false
function saveUserMessage(){
	const queryString = window.location.href;
	let channelId = queryString.substring(queryString.lastIndexOf("/") + 1, queryString.length);
			let user = {
				'id': channelId,
				'username' : test,
				'message' : [{
					'message' : inputText.value
				}],
				'channel' : [{
					'id' : channelId
				}]
			}
	let responseEntity = fetch('/saveMess', {
					method: 'POST',
					headers: {
						'Content-type': 'application/json'
					},
					body: JSON.stringify(user)
				})
}
inputText.addEventListener('keydown', (e) => {
	if(e.shiftKey) shiftPress = true
	if(e.keyCode == 13){
		e.preventDefault()
		enterPress = true
	}
})
inputText.addEventListener('keyup', (e) => {
	if(e.shiftKey || e.keyCode == 13){
		if(!enterPress) shiftPress = false
		else{
			if(!shiftPress){
				saveUserMessage()
				e.preventDefault
				inputText.value = ''
				enterPress = false
			} else{
				inputText.value += "\n"
				enterPress = false
				shiftPress = false
			}
		}
	}
})
function getMessages(){
	const queryString = window.location.href;
	let channelId = queryString.substring(queryString.lastIndexOf("/") + 1, queryString.length);
	let message = [];
	message = fetch('/channel/'+ new URLSearchParams({channelId})+'/getMessages', {
		method : 'POST',
		headers : {
			'Content-Type': 'application/json'
		}
	}).then(response => response.json()).then(function (data){ 
			appendMessages(data)
		
	})
	
	
}
	function appendMessages(data){
		var messageBox = document.getElementById('messageBox')
		messageBox.innerHTML = ''
		for(var i = 0; i < data.legnth; i++){
			var div = document.createElement('div');
			div.innerHTML = data[i].messageName + ": " + data[i].messageContent;
			messageBox.appendChild(div);
		}
	}
setInterval(getMessages, 5000)