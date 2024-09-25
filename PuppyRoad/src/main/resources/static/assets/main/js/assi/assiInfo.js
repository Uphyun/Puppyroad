/*
 * matchList.js
 */
 const context = $('meta[name="contextPath"]').attr('value');

function application(event){
	let bulletinNo = $('input[name=bulletinNo]').val();
	let writer = $('input[name=writer]').val();
	let title = $('input[name=title]').val();
	let url = context + '/user/matchChat?writer=' + writer + '&title=' + title + '&chattingType=1' + '&bulletinNo=' + bulletinNo ;
	location.href = url;
	
}
