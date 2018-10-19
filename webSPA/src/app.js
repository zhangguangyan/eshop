document.addEventListener("DOMContentLoaded", function(){
    fetch('/api/v1/catalog/items')
        .then(res => res.text())
        .then(text => document.getElementById("root").innerHTML = text)
        .catch(error => {
            console.log(error);
        });
});
