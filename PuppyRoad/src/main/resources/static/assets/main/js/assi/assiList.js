/*
 * assiList.js
 */

 const context = $('meta[name="contextPath"]').attr('value');
$('.clicker').on('click', function(event){
	let bno = $(event.currentTarget).attr("id");
	let url = context + '/user/assiInfo?bulletinNo=' + bno;
	location.href = url;
})