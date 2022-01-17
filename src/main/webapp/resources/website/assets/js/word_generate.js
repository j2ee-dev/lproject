$(function() {
    $(document).on('click','.wordfile',function () {
        var baseUrl = "http://localhost:8083";
        var relativeUrl=$(this).prop('name');
        var sourceUrl = baseUrl + relativeUrl;
        var splt=relativeUrl.split(/[\/.]+/);
        var fileName = splt[splt.length-2];
        var paths = sourceUrl;
        //var paths = "file:///E:/LAW/Districts.html";
        console.log(paths);
        $.ajax({
            type: "GET",
            url: "http://192.168.10.28:8089/footnoteapi/api/values/",
            data: {path: paths, fileName: fileName},
            dataType: "xml",

            success: function (data, textStatus, xhr) {
                alert("Conversion done");
                console.log(data);
                console.log(textStatus);
                console.log(xhr);

            },

            error: function (xhr, textStatus, errorThrown) {
                alert("Conversion done");
            }

        });
    });
});