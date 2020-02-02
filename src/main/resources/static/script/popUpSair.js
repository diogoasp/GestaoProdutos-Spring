function hiddenChange() {
  var elem = this.nextElementSibling;
  
  if(elem.hidden === false){
    elem.hidden = true;
  }else {
    elem.hidden = false;
  }
}

let mostrar = document.getElementById("mostrar");

mostrar.addEventListener('click', hiddenChange);