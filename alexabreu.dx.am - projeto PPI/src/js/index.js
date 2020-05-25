function scrollToView(divName)
{
    var element = document.getElementById(divName);
    element.scrollIntoView();
}

function createTable() {

  var table = document.getElementById("listF");

  var i;
  var j = 0;
  for(i = 0 ; i <= 10; i++) {
  	let row = table.insertRow(i);
 	let cell1 = row.insertCell(j);
 	cell1.innerHTML = "Fulano da Silva";
  
  }

}

function listCliente() {

  var table = document.getElementById("listC");

  var i;
  var j = 0;
  for(i = 0 ; i <= 10; i++) {
  	let row = table.insertRow(i);
 	let cell1 = row.insertCell(j);
 	cell1.innerHTML = "Fulano da Silva";
  
  }

}


function listInt() {

  var table = document.getElementById("listI");

  var i;
  var j = 0;
  for(i = 0 ; i <= 10; i++) {
  	let row = table.insertRow(i);
 	for (j = 0; j <= 5; j++) {
 		let cell1 = row.insertCell(j);
 		if (j == 0) {
 			cell1.innerHTML = "imóvel";
 		}
 		else{
 			cell1.innerHTML = "Fulano da Silva";
 		}
 		
 		
 	}
  
  }

}


function listImovel() {

  var table = document.getElementById("listIM");

  var i;
  var j = 0;
  for(i = 0 ; i <= 10; i++) {
  	let row = table.insertRow(i);
 	let cell1 = row.insertCell(j);
 	cell1.innerHTML = "imóvel ID";
  
  }

}