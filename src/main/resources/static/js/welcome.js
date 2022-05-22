let username = prompt('Enter your name')
let user = {
	'username' : username
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
saveUserMethod()

sessionStorage.setItem('user', JSON.stringify(user))