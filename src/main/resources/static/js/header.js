$(".endArea").blur(function () {
    var end = $(".endArea").val();
    var start = $(".startArea").val();
    if (start > end) {
        $(".endArea").val(start)
    }
})
$(".endPrice").blur(function () {
    var end = $(".endPrice").val();
    var start = $(".startPrice").val();
    if (start > end) {
        $(".endPrice").val(start)
    }
})
$(".btn-chothue").click(function () {
    window.location.href = "http://localhost:8080/post";
})
$(".btn-timtro").click(function () {
    window.location.href = "http://localhost:8080/postFindRoom";
})
$(".btn-login").click(function () {
    window.location.href = "http://localhost:8080/login";
})
$(".btn-register").click(function () {
    window.location.href = "http://localhost:8080/login";
})
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
                $("#Type").append("<option value=" + type.id + ">" + type.name + "</option>")
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
                $("#distric").append("<option value=" + distric.id + ">" + distric.prefix + " " + distric.name + "</option>")
            }
        }
    )

function getward() {
    $(".ward").remove();
    var districid = document.getElementById("distric").value;
    fetch('http://localhost:8080/wards/' + districid, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
        .then(res => res.json())
        .then(data => {
                console.log(data)
                if (Object.keys(data).length === 0) {
                    $(".wards").append("<option class='ward' value='0'> </option>")
                }
                for (ward of data) {
                    $(".wards").append("<option class='ward' value=" + ward.id + ">" + ward.prefix + " " + ward.name + "</option>")
                }
            }
        )
}