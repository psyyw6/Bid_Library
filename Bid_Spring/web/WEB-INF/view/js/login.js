$(document).ready(function () {
    $(".cal-image").click(function () {
        // alert("hhhh");
        if ($(".options").css("visibility") == "hidden") {
            $(".options").css("visibility", "visible");
        }
        else {
            $(".options").css("visibility","hidden");
        }

    });

});