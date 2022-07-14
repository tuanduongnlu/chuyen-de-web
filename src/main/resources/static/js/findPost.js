fetch('http://localhost:8080/roomtypes', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
})
    .then(res => res.json())
    .then(data => {
            for (type of data) {
                $("#typePost").append("<option value=" + type.id + ">" + type.name + "</option>")
            }
        }
    )