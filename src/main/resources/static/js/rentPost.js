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
fetch('http://localhost:8080/districs', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
})
    .then(res => res.json())
    .then(data => {
            for (distric of data) {
                $("#distric-post").append("<option value=" + distric.id + ">" + distric.prefix + " " + distric.name + "</option>")
            }
        }
    )

function getwardPost() {
    $(".ward-post").remove();
    var districid = document.getElementById("distric-post").value;
    fetch('http://localhost:8080/wards/' + districid, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
        .then(res => res.json())
        .then(data => {
                if (Object.keys(data).length === 0) {
                    $("#wards-post").append("<option class='ward-post' value='0'> </option>")
                }
                for (ward of data) {
                    $("#wards-post").append("<option class='ward-post' value=" + ward.id + ">" + ward.prefix + " " + ward.name + "</option>")
                }
            }
        )
}

$("#file").change(function () {
    var fi = document.getElementById('file');
    if (fi.files.length > 1) {
        $("#fp").remove();
        for (var i = 0; i <= fi.files.length - 1; i++)
            $("#show-name-image").append("<p id=\"fp\">" + fi.files.item(i).name + '</p>')
    } else $("#fp").remove();

})