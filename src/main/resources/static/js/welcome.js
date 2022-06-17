var userCheck = sessionStorage.getItem('user')
let username
let user
if(userCheck === null){
	username = prompt('Enter your name')
	while( username ===  null || username == ""){
	username = prompt("Enter Your Name")
	} 
	user = {
		'username' : username
	}
	saveUserMethod()
	sessionStorage.setItem('user', JSON.stringify(user))
	} else {
		user = JSON.parse(sessionStorage.getItem("user"))
	}
async function saveUserMethod(){
	let responseEntity = await fetch("/saveUser", {
		method:'POST',
		headers:{
			'Content-type': 'application/json'
		},
		body: JSON.stringify(user)
	})
	let userExist = await responseEntity.json()
		if(userExist === true){
			console.log('User Created')
		}
}
