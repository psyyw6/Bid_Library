function viewSection(obj){
    var target = $(".section_div");
    var index = $(obj).parents("tr").find("#index").val();
    $(target[index]).toggle(500);
}