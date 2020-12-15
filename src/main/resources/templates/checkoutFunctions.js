//Based off sample template found online. Using HTML5 local storage temporarily but teamates will transition the functions to database calls.


//Receives info from forms and sends to database. To be filled by team mate.
function CheckOut() {
    //send info to database.
}


//Pulled code from the Cart section. Will need to update so its just a call for the existing list.
function Update() {
	if (browserCheck()) {
		var key = "";
		var list = "<tr><th>Item</th><th>Value</th></tr>\n";
		var i = 0;
		for (i = 0; i <= localStorage.length-1; i++) {
			key = localStorage.key(i);
			list += "<tr><td>" + key + "</td>\n<td>" + localStorage.getItem(key) + "</td></tr>\n";
		}
		//if there's an empty cart
		if (list == "<tr><th>Item</th><th>Value</th></tr>\n") {
			list += "<tr><td><i>empty</i></td>\n<td><i>empty</i></td></tr>\n";
		}
		//sending data to html table. Will eventually be a call for the database here.
		document.getElementById('list').innerHTML = list;
	}
}

//Checks browser for HTML compatibility.
function browserCheck() {
	if(window.localStorage!==undefined){
        return true;
    }
    else{
        alert('Please update your browser!');
        return false;
    }