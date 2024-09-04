/*
 * matchList.js
 */

 const context = $('meta[name="contextPath"]').attr('value');
$('tbody > tr').on('click', function(event){
	let bno = $(event.currentTarget).children().first().text();
	let url = context + '/matchInfo?bulletinNo=' + bno;
	location.href = url;
})