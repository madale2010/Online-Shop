
/**
 * Open image for gallery view.
 * @param id
 */	
function openOnImageClick(id)
{
	var image = document.getElementById(id).src;
  	//alert(image);
  	document.getElementById("gallaryImage").src=image;
}
/**
 * Go back method
 */
function goBack() {
    window.history.back();
}
/**
 * Method to allow override of bootstrap click
 * @param clicked_id
 */
function reply_click(clicked_id)
{
    window.location.href =clicked_id;
}
/**
 * 
 * @returns {Boolean}
 */
function submit(){
	if(confirm('Are you sure you want to delete?')){
		return true;
	} else {
		return false;
	}
}
/**
 * Jquery to get the order table and on click send id that we need to load.
 */
$("#blogTable tr td").on( "click", function() {
	var $row = $(this).closest("tr"),        // Finds the closest row <tr> 
    $tds = $row.find("td:nth-child(1)");
	var input = $("<input>").attr("type", "hidden").attr("name", "action").val("updateManager");
	var input2 = $("<input>").attr("type", "hidden").attr("name", "blogId").val($tds.text());

	$('#UpdateBlog').append($(input));
	$('#UpdateBlog').append($(input2));
	$('#UpdateBlog').submit();
});
/**
 * Jquery to get the order table and on click send id that we need to load.
 */
$( "#productTable tr td" ).on( "click", function() {
	var $row = $(this).closest("tr"),        // Finds the closest row <tr> 
    $tds = $row.find("td:nth-child(1)");
	var $clickedCell = $(this).attr('id');
	
	if($clickedCell!=='featured' && $clickedCell!=='showItem'){
		
		var input = $("<input>").attr("type", "hidden").attr("name", "update").val($tds.text());
		$('#UpdateProduct').append($(input));
		$('#UpdateProduct').submit();
	}
});
$("#productTable tr td input").change(function(){
	var $row = $(this).closest("tr"),        // Finds the closest row <tr> 
    $tds = $row.find("td:nth-child(1)");
	var $clickedCell = $(this).attr('id');
	var input = $("<input>").attr("type", "hidden").attr("name", "action").val("fastUpdate");
	var input2 = $("<input>").attr("type", "hidden").attr("name", "pid").val($tds.text());
	if($(this).is(":checked")){
		var input3 = $("<input>").attr("type", "hidden").attr("name", $clickedCell).val("Y");
	} else {
		var input3 = $("<input>").attr("type", "hidden").attr("name", $clickedCell).val("N");
	}
	$('#UpdateProduct').append($(input));
	$('#UpdateProduct').append($(input2));
	$('#UpdateProduct').append($(input3));
	$('#UpdateProduct').submit();
});
/**
 * Jquery to get the order table and on click send id that we need to load.
 */

$.fn.dataTable.ext.order['dom-checkbox'] = function  ( settings, col )
{
    return this.api().column( col, {order:'index'} ).nodes().map( function ( td, i ) {
        return $('input', td).prop('checked') ? '1' : '0';
    } );
};
///**
// * Jquery to get the order table and on click send id that we need to load.
$(document).ready( function () {
	 $('#productTable').DataTable({
    	
    	 paging: false,
    	 colReorder: true,
    	 scrollY: 240,
    	 "order": [ 0, 'desc' ],
    	 "columnDefs": [
                       
    	                { "orderDataType": "dom-checkbox", "targets": 2 },
    	                { "orderDataType": "dom-checkbox", "targets": 3 },
    	                { "orderable": false, "targets": 9 }
    	               ]
    	
    });
} );
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#logoDisplay').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
//function
$("#logoImage").change(function(){
    readURL(this);
});
$("#carouselImages").change(function(){
   
        var fileList = this.files;
	
        var anyWindow = window.URL || window.webkitURL;

            for(var i = 0; i < fileList.length; i++){
              //get a blob to play with
              var objectUrl = anyWindow.createObjectURL(fileList[i]);
              // for the next line to work, you need something class="preview-area" in your html
              $('.preview-area').append('<img src="' + objectUrl + '" width="100" height="100"/>');
              // get rid of the blob
              window.URL.revokeObjectURL(fileList[i]);
            }
    });
    //Function to append to catagory select box
    $("#addCategoryBtn").on( "click", function() {
    	 $('#ConfigSettings').submit();
    });
    //Function
    $( "#addSubCategoryBtn").on( "click", function() {
    $('#ConfigSettings').submit();
    });
 /**
  * Function used in tinymce to upload files to server.
  * @param event
  * @param callback
  * @returns
  */
 function onFileChosen(event,callback)
 {
   //Detach any current submit handlers
   $("#fileUploadForm").unbind("submit");
   $("#fileUploadForm").submit(function(e) {
  
     e.stopPropagation(); // Stop stuff happening
     e.preventDefault(); // Totally stop stuff happening
          
     //Prepare file in form for transmission via ajax call
     var formData = new FormData();
     $.each(event.target.files, function(i, file) {
         formData.append('file-'+i, file);
     });
  
     //The url that will handle the file upload
     var url = "../UploadFile"
  
     //Do ajax call
     $.ajax({
       type: "POST",
       url: url,
       data: formData,
       cache: false,
       contentType: false,
       processData: false,
       success: function(data)
       { 
     	
         //You can process the response however you want, but I chose to return a json string
        // var response = JSON.parse(data)[0];
         //alert("Json : ");
        
 		callback(data);
       },
     });
  
   });
   $("#fileUploadForm").submit();
 }
 tinymce.init({
	  selector: '#tinymce',
	  height: 500,
	  theme: 'modern',
	  plugins: [  
	    'advlist autolink lists link image charmap print preview hr anchor pagebreak',
	    'searchreplace wordcount visualblocks visualchars code fullscreen',
	    'insertdatetime media nonbreaking save table contextmenu directionality',
	    'emoticons template paste textcolor colorpicker textpattern imagetools'
	  ],
	  save_enablewhendirty : false,
	  toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
	  toolbar2: 'print preview media | forecolor backcolor emoticons',
	  image_advtab: true,
	  templates: [
	    { title: 'Test template 1', content: 'Test 1' },
	    { title: 'Test template 2', content: 'Test 2' }
	  ],
	  content_css: [
	    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
	    '//www.tinymce.com/css/codepen.min.css'
	  ],
	  file_picker_callback: function(callback, value, meta) {
         if (meta.filetype == 'image') {
             $("#fileUploader").unbind( "change" );
             $("#fileUploader").change(function(event) { onFileChosen(event,callback);});
             $("#fileUploader").click();
         }
         if (meta.filetype == 'file') {
             $("#fileUploader").unbind( "change" );
             $("#fileUploader").change(function(event) { onFileChosen(event,callback);});
             $("#fileUploader").click();
         }
     },
     convert_urls: false
	 });
 /**
  * Load product description editor, this should be inline to make it smaller
  */
	tinymce.init({
		 selector: '#description',
		  height: 150,
		  theme: 'modern',
		  plugins: [  
		    'advlist autolink lists link image charmap print preview hr anchor pagebreak',
		    'searchreplace wordcount visualblocks visualchars code fullscreen',
		    'insertdatetime media nonbreaking save table contextmenu directionality',
		    'emoticons template paste textcolor colorpicker textpattern imagetools'
		  ],
		  save_enablewhendirty : false,
		  toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
		  toolbar2: 'print preview media | forecolor backcolor emoticons',
		  image_advtab: true,
		  templates: [
		    { title: 'Test template 1', content: 'Test 1' },
		    { title: 'Test template 2', content: 'Test 2' }
		  ],
		  content_css: [
		    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
		    '//www.tinymce.com/css/codepen.min.css'
		  ],
		  file_picker_callback: function(callback, value, meta) {
	         if (meta.filetype == 'image') {
	             $("#fileUploader").unbind( "change" );
	             $("#fileUploader").change(function(event) { onFileChosen(event,callback);});
	             $("#fileUploader").click();
	         }
	     },
	     convert_urls: false
		 });