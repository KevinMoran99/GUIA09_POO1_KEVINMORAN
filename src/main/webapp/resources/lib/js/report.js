$(document).ready(function() {
    
    $.fn.openViewer = function () {
        $(this).submit(function(e) {
            e.preventDefault();
            var ctxt = $(this).data("ctxt");
            /*var repo = $(this).data("repo");
            var prms = $(this).data("prms");*/
            var repoURL = ctxt + "/WarehouseServ?teamBtn=Reporte&from=" + $("#from").val() + "&to=" + $("#to").val();
            var options = {
                pdfOpenParams: {
                        navpanes: 0,
                        toolbar: 0,
                        statusbar: 0,
                        view: "FitV",
                        pagemode: "none",
                        page: 1
                },
                forcePDFJS: true,
                PDFJS_URL: ctxt + "/resources/lib/pdfjs/web/viewer.html"
            };
            var myPDF = PDFObject.embed(repoURL, "#pdfViewer", options);
            $("#modalReport").modal("show");
            return false;
        });
    };
    initObjeRepo();
});

function initObjeRepo()
{
    $("#reportForm").openViewer();
}

