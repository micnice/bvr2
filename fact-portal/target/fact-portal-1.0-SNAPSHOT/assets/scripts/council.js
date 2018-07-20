//Static Data Style Button and Set date Pick for Date Retired
$(function() {

    $("#endLeaveDate").datepicker({
        changeYear: true,
        changeMonth: true,
        dateFormat: "dd/mm/yy",
        yearRange: "1930:2050"
    });
    $("#dateApprovedByBoard").datepicker({
        changeYear: true,
        changeMonth: true,
        dateFormat: "dd/mm/yy",
        yearRange: "2010:2050"
    });    
});
