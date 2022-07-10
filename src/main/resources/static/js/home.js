function clickPage(page) {
    $(".room").remove() ;
    var value = document.getElementById("filter").value ;
    fetch('http://localhost:8080/posts/'+page + '/' + value + '/' + roomType,{
        method: 'POST',
        headers : {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
        .then(res => res.json())
        .then(data => {
            addRoom(data)
        })
}

$(function() {
    $("#pagination").pagination({
        pages: totalpage,
        cssStyle: 'light-theme',
        displayedPages: 3,
        onPageClick: function (page,evt){
            clickPage(page)
        }
    });

});
function addRoom(e){
    for(const room of e) {
        if(room.images.length==0) {
            $(".list-room").append(
                " <div class=\"col-md-4 room\" id='room'>\n" +
                "                        <div class=\"card-box-a card-shadow\">\n" +
                "                            <div class=\"img-box-a\">\n" +
                "                            <img  src=\"images/noimage.png\"  class=\"img-a img-fluid\">" +
                "                            </div>\n" +
                "                            <div class=\"card-overlay\">\n" +
                "                                <div class=\"card-overlay-a-content\">\n" +
                "                                    <div class=\"card-header-a\">\n" +
                "                                        <h2 class=\"card-title-a\">\n" +
                "                                            <a href=\"posts/" + room.id + "\">" + room.location.distric + "\n" +
                "                                                <br /> " + room.location.ward + "</a>\n" +
                "                                        </h2>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"card-body-a\">\n" +
                "                                        <div class=\"price-box d-flex\">\n" +
                "                                            <span class=\"price-a\">Giá | " + room.price + "  VND</span>\n" +
                "                                        </div>\n" +
                "                                        <a class='link-a' href=\"/posts/" + room.id + "\" >" + "Click để xem chi tiết" + "</a>" +
                "                                            <span class=\"bi bi-chevron-right\"></span>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"card-footer-a\">\n" +
                "                                        <ul class=\"card-info d-flex justify-content-around\">\n" +
                "                                            <li>\n" +
                "                                                <h4 class=\"card-info-title\">Diện tích</h4>\n" +
                "                                                <span>" + room.acreage + "m" + "\n" +
                "                          <sup>2</sup>\n" +
                "                        </span>\n" +
                "                                            </li>\n" +
                "                                            <li>\n" +
                "                                                <h4 class=\"card-info-title\">Loại phòng</h4>\n" +
                "                                                <span>" + room.roomType.name + "</span>\n" +
                "                                            </li>\n" +
                "                                            <li>\n" +
                "                                                <h4 class=\"card-info-title\">Ngày đăng</h4>\n" +
                "                                                <span>" + room.timePost + "</span>\n" +
                "                                            </li>\n" +
                "                                            <li>\n" +
                "                                                <h4 class=\"card-info-title\">Tình trạng</h4>\n" +
                "                                                <span>" + room.status + "</span>\n" +
                "                                            </li>\n" +
                "                                        </ul>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>"
            )
        }
        if(room.images.length!=0) {
            $(".list-room").append(
                " <div class=\"col-md-4 room\" id='room'>\n" +
                "                        <div class=\"card-box-a card-shadow\">\n" +
                "                            <div class=\"img-box-a\">\n" +
                "                            <img  src=\"" + room.images[0].link + "\"  class=\"img-a img-fluid\">\n" +
                "                            </div>\n" +
                "                            <div class=\"card-overlay\">\n" +
                "                                <div class=\"card-overlay-a-content\">\n" +
                "                                    <div class=\"card-header-a\">\n" +
                "                                        <h2 class=\"card-title-a\">\n" +
                "                                            <a href=\"posts/" + room.id + "\">" + room.location.distric + "\n" +
                "                                                <br /> " + room.location.ward + "</a>\n" +
                "                                        </h2>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"card-body-a\">\n" +
                "                                        <div class=\"price-box d-flex\">\n" +
                "                                            <span class=\"price-a\">Giá | " + room.price + "  VND</span>\n" +
                "                                        </div>\n" +
                "                                        <a class='link-a' href=\"/posts/" + room.id + "\" >" + "Click để xem chi tiết" + "</a>" +
                "                                            <span class=\"bi bi-chevron-right\"></span>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"card-footer-a\">\n" +
                "                                        <ul class=\"card-info d-flex justify-content-around\">\n" +
                "                                            <li>\n" +
                "                                                <h4 class=\"card-info-title\">Diện tích</h4>\n" +
                "                                                <span>" + room.acreage + "m" + "\n" +
                "                          <sup>2</sup>\n" +
                "                        </span>\n" +
                "                                            </li>\n" +
                "                                            <li>\n" +
                "                                                <h4 class=\"card-info-title\">Loại phòng</h4>\n" +
                "                                                <span>" + room.roomType.name + "</span>\n" +
                "                                            </li>\n" +
                "                                            <li>\n" +
                "                                                <h4 class=\"card-info-title\">Ngày đăng</h4>\n" +
                "                                                <span>" + room.timePost + "</span>\n" +
                "                                            </li>\n" +
                "                                            <li>\n" +
                "                                                <h4 class=\"card-info-title\">Tình trạng</h4>\n" +
                "                                                <span>" + room.status + "</span>\n" +
                "                                            </li>\n" +
                "                                        </ul>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>"
            )
        }
    }
}
function filterRoom() {
    $(function() {
        $("#pagination").pagination('selectPage', 1);
    });

}