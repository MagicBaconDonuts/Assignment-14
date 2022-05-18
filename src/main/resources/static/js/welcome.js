let username = prompt('Enter your name')
let user = {
	'username' : username
}
fetch('/saveUser', {
	method:'POST',
	headers:{
		'Content-type': 'application/json'
	},
	body: JSON.stringify(user)
})
.then((response) => {
	return response.json()
})