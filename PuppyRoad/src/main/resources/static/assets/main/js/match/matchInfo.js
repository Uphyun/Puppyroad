/*
 * matchList.js
 */
 const context = $('meta[name="contextPath"]').attr('value');

function application(event){
	let writer = $('input[name=writer]').val();
	let title = $('input[name=title]').val();
	let url = context + '/user/matchChat?writer=' + writer + '&title=' + title + '&chattingType=1' ;
	location.href = url;
	
}
