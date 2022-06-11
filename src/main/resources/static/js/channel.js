let test = sessionStorage.getItem('user')
console.log(test)
let inputText = document.querySelector('#message')
console.log(inputText.value)
let shiftPress = false
let enterPress = false
function saveUserMessage(){
	let channelId = document.querySelector('#channelId')
			let user = {
				'id': channelId.value,
				'username' : test,
				'message' : [{
					'message' : inputText.value
				}],
				'channel' : [{
					'id' : channelId.value
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
setInterval(getMessages, 500)
function getMessages(){
	let channelId = document.querySelector('#channelId')
	fetch('/channel/${channelId}/getMessages', {
		method : 'POST',
		headers : {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(channelId.value)
	})/*.then(response => response.json().then((data) => {
		var messageBox = document.getElementById('messageBox')
		messageBox.innerHTML = ''
		for(var i = 0; i < data.legnth; i++){
			var element = document.createElement('element')
			element.innerHTML = data[i].messageName
		}
	}))*/
}


