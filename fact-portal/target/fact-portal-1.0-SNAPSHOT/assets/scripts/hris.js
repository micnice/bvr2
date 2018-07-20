//Static Data Style Button and Set date Pick for Date Retired
jQuery(function() {
    
    $("#au_dialog").dialog({
        autoOpen: false,
        width: 800,
        buttons: {
            close: function() {
                $("#au_dialog").dialog("close");
            }
        }
    });
    $("#transhistory").click(function() {
        $("#au_dialog").dialog("open");
    });    
//
//    $("#transferEndDate").datepicker({
//        changeYear: true,
//        changeMonth: true,
//        dateFormat: "dd/mm/yy",
//        yearRange: "1930:2050"
//    });
//
    $("#tableList").dataTable({
        "bFilter": true,
        "sPaginationType": "full_numbers"
    });
     
    //$("#tabs").tabs();

});
