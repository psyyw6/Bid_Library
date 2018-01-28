$(document).ready(function () {
    $(".cal-image").click(function () {
        if ($(".options").css("visibility") == "hidden") {
            $(".options").css("visibility", "visible");
        }
        else {
            $(".options").css("visibility","hidden");
        }

    });

});