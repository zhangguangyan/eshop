const url = '/api/v1/catalog/items';
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("save").onclick = function () {
        var name = document.getElementById("name").value
        console.log(name);
        fetch(url, {
                method: 'POST', // or 'PUT'
                body: JSON.stringify({
                    name: name
                }), // data can be `string` or {object}!
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.status)
            .then(response => {
                console.log('Success:', response);
                fetch(url)
                    .then(res => res.text())
                    .then(text => document.getElementById("output").innerHTML = text)
                    .catch(error => console.log(error));
            })
            .catch(error => console.error('Error:', error));
    };
});
