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
						  
						  
	let results = $.ajax({
							url : "/user/SchedulePayList",
							method : "GET",
							 async: false
						  }).responseText;
		
	let newData=[]; //정산일
	results = JSON.parse(results)
	for(let item of results){
		newData.push({
			start : item.nextFriday,
			title : '정산일',
			totalPrice : item.totalPrice,
			monday : item.monday,
			sunday : item.sunday,
			extendedProps: {
      			calendar: 'Holiday'
   			 }
		})
	};
	datas = JSON.parse(datas)
	for(let res of datas){
		if(res.clientName != null){
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
				})
		};
			
	
	}
		for(let res of datas){
			if(datas.length > 0){
				newData.push({
					title : '휴가',
					start : res.holidayStart,
					end : res.holidayEnd,
					extendedProps: {
		      			calendar: 'Personal'
		   			 }
					})
			}
		};
	return newData;

}

window.events = test();