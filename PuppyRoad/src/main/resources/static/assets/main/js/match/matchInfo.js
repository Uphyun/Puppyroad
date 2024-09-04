/*
 * matchList.js
 */

const context = $('meta[name="contextPath"]').attr('value');
$('#modifyBtn').on('click', function(event){
	let bno = $('#bulletinNo').val();
	let url = context + '/matchUpdate?bulletinNo=' + bno;
	location.href = url;
})