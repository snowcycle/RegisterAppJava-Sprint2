//Based off sample template found online. Using HTML5 local storage temporarily but teamates will transition the functions to database calls.

//Adds a new item to local storage. Will need to replace with a poll to the database.
function Save() {
			
	var name = document.forms.ShoppingList.name.value;
	var info = document.forms.ShoppingList.data.value;
	localStorage.setItem(name, info);
	Update();
	
}


//Modifies an Existing Item in the database.
function Modify() {
	var nameCheck = document.forms.ShoppingList.name.value;
	var dataCheck = document.forms.ShoppingList.data.value;
	
//Check for duplicates
if (localStorage.getItem(nameCheck) !=null)
			{
			  localStorage.setItem(nameCheck,dataCheck);
			  document.forms.ShoppingList.data.value = localStorage.getItem(nameCheck);
                //getItem will be implemented by another member
			}
	Update();
}

//Remove an Item from the Cart
function Remove() {
	var name = document.forms.ShoppingList.name.value;
	document.forms.ShoppingList.data.value = localStorage.removeItem(name);
	Update();
}

//clearing storage, will eventually be another call to the database to delete user cart.
function ClearStorage() {
	localStorage.clear();
	Update();
}


//For updating the list after various functions. Heavily based on code found online.
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