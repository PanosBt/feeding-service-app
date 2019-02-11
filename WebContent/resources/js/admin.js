$(document).ready(function(){
	$("select#userType").change(function() {
		var id = $(this).find("option:selected").attr("id");
		if (id === 'student_option')
			$("#student_dept").fadeIn("slow");
		else
			$("#student_dept").fadeOut("slow");
	});
});