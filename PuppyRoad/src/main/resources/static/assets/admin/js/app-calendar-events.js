/**
 * App Calendar Events
 */

'use strict';

let date = new Date();
let nextDay = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
// prettier-ignore
let nextMonth = date.getMonth() === 11 ? new Date(date.getFullYear() + 1, 0, 1) : new Date(date.getFullYear(), date.getMonth() + 1, 1);
// prettier-ignore
let prevMonth = date.getMonth() === 11 ? new Date(date.getFullYear() - 1, 0, 1) : new Date(date.getFullYear(), date.getMonth() - 1, 1);
/**{
    id: 2,
    url: '',
    title: 'Meeting With Client',
    start: new Date(date.getFullYear(), date.getMonth() + 1, -11),
    end: new Date(date.getFullYear(), date.getMonth() + 1, -10),
    allDay: true,
    extendedProps: {
      calendar: 'Business'
    }*/
function test(){
	let datas =  $.ajax({
							url : "/user/scheduleListprocess",
							method : "GET",
							 async: false
						  }).responseText;
							  
	let newData=[];
	datas = JSON.parse(datas)
	console.log(typeof datas);
	for(let res of datas){
		console.log(datas);
		newData.push({
			start : res.startTime,
			end : res.endTime,
			title : res.clientName,
			dogName : res.dogName,
			walkFare : res.walkFare,
			address : res.address,
			phone : res.phone,
			extendedProps: {
      			calendar: 'Business'
   			 }
			});
		newData.push({
			title : res.scheduleTitle,
			start : res.holidayStart,
			end : res.holidayEnd,
			extendedProps: {
      			calendar: 'Personal'
   			 }
			});
	
	}
	return newData;

}

window.events = test();