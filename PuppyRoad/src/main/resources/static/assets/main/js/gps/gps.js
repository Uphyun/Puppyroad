/**
 * gps.js
 */
/**
 * 본 스크립트 이전 선언 필수
 * 제이쿼리 필수
 * <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=22529f2e0edbc8b92825acd8646989cb&libraries=drawing"></script>
 */


//변수 집합소
let manager;            //그리기도구(?)
let walkingInterval;           //산책할 때 쓸 반복실행
let matchingInterval;           //매칭할 때 쓸 반복실행
let overlays = [];      //지도에 존재하는 선목록

let mapContainer;
let mapOption;
let map;
let drawOptions;
let markers = [];
let naviOption = {
	enableHighAccuracy: true,
	timeout: 5000,
	maximumAge: 0,
}

//지도 설정
function setMap(mapWrap = "map") {
	mapContainer = document.getElementById(mapWrap); // 지도를 표시할 div 

	try {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				console.log("인식완료");
				const lat = position.coords.latitude;
				const lon = position.coords.longitude;

				mapOption = {
					center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표(현재위치)
					level: 2
				};

				map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
				setDrawing();
			}, error, naviOption);
		} else {
			mapOption = {
				center: new kakao.maps.LatLng(35.8691089, 128.593387), // 지도의 중심좌표(예담)
				level: 2 // 지도의 확대 레벨 
			};

			map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
			setDrawing();

			alert("gps 확인 부탁드립니다.");
		}
	} catch (error) {
		console.log(error);
	}


};

function error(err) {
	console.log(err);
}

//그리기 세팅
function setDrawing() {
	//그리기 도구 설정
	drawOptions = { // Drawing Manager를 생성할 때 사용할 옵션입니다
		map: map, // Drawing Manager로 그리기 요소를 그릴 map 객체입니다
		drawingMode: [ // Drawing Manager로 제공할 그리기 요소 모드입니다
			kakao.maps.Drawing.OverlayType.POLYLINE],
		// 사용자에게 제공할 그리기 가이드 툴팁입니다
		// 사용자에게 도형을 그릴때, 드래그할때, 수정할때 가이드 툴팁을 표시하도록 설정합니다
		guideTooltip: ['draw', 'drag', 'edit'],
		polylineOptions: { // 선 옵션입니다
			draggable: false, // 그린 후 드래그가 가능하도록 설정합니다
			removable: false, // 그린 후 삭제 할 수 있도록 x 버튼이 표시됩니다
			editable: true, // 그린 후 수정할 수 있도록 설정합니다 
			strokeColor: '#39f', // 선 색
			hintStrokeStyle: 'dash', // 그리중 마우스를 따라다니는 보조선의 선 스타일
			hintStrokeOpacity: 0.5
			// 그리중 마우스를 따라다니는 보조선의 투명도
		}
	};

	// 위에 작성한 옵션으로 Drawing Manager를 생성합니다
	manager = new kakao.maps.Drawing.DrawingManager(drawOptions);
}

//실시간 경로그리기(산책시작)
function startWalking(code = '0000000001', used = 'puppy') {
	//used => puppy, schedule, journal;
	let turn = 0;

	alert("산책을 시작합니다!");

	walkingInterval = setInterval(function() {
		// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
		if (navigator.geolocation) {
			// GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {

				let lat = position.coords.latitude; // 위도(세로-y)
				let lon = position.coords.longitude; // 경도(가로-x)

				let sendData = setData(code, used, lon, lat, turn);
				turn++;

				//경로 등록 및 부르기
				let locations = [];
				let url = "/ajax/callNavi";
				$.ajax({
					url,
					method: 'post',
					contentType: "application/json",
					data: JSON.stringify(sendData),
				})
					.done(datas => {
						console.log(datas);
						datas.forEach(data => {
							locations.push({
								turnNo: data.turnNo,
								x: data.x,
								y: data.y
							});
						});

						mapOption.center = new kakao.maps.LatLng(lat, lon);

						map = new kakao.maps.Map(mapContainer, mapOption);

						//선 삭제
						removeOverlays();

						//선그리기
						drawPolylineNow(locations);
					})
					.fail(err => console.log(err));
			}, error, naviOption);

		} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

			alert('위치 정보를 가져올 수 없습니다.');
		}
	}, 10000);
}

//보낼 데이터 세팅
function setData(code, used, x, y, turnNo = 0) {
	let data = {
		turnNo,
		x,
		y
	};

	// puppyCode, scheduleCode, journalCode;
	if (used == 'puppy') {
		data.puppyCode = code;
	} else if (used == 'schedule') {
		data.scheduleCode = code;
	} else if (used == 'journal') {
		data.journalCode = code;
	} else if (used == 'match') {
		data.matchCode = code;
	}

	return data;
}

//선 그리기(경로 갯수에 따라 for문 사용)
function drawPolylineNow(lines) {

	let path = pointsToPathNow(lines);
	let polyline = new kakao.maps.Polyline({
		map: map,
		path: path,
		strokeColor: "#39f",
		strokeOpacity: 1,
		strokeStyle: "solid",
		strokeWeight: 8
	});

	overlays.push(polyline);

}

//선의 경로만들기(경로 하나의 점의 갯수)
function pointsToPathNow(points) {
	let len = points.length,
		path = [],
		i = 0;

	for (; i < len; i++) {
		let latlng = new kakao.maps.LatLng(points[i].y, points[i].x);
		path.push(latlng);
	}

	return path;
}

//선 삭제
function removeOverlays() {
	let len = overlays.length, i = 0;

	for (; i < len; i++) {
		overlays[i].setMap(null);
	}

	overlays = [];
}

//산책종료
function stopWalk() {
	alert("산책을 종료합니다!");
	clearInterval(walkingInterval);
}

//매칭 시작
function startMatching(matchCode, used = 'match') {
	console.log(matchCode, used);
	// debugger
	alert("매칭을 시작합니다!");
	let timing = 0;
	let km = 1;
	let sendData;
	let lat, lon;

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {

			lat = position.coords.latitude; // 위도(세로-y)
			lon = position.coords.longitude; // 경도(가로-x)

			sendData = setData(matchCode, used, lon, lat, 0);

			$.ajax({
				method: "post",
				url: "/ajax/setMatch",
				contentType: "application/json",
				data: JSON.stringify(sendData),
			})
				.done(result => {
					if (result.isSuccess) {
						console.log("성공");
					}
				})
				.fail(err => console.log(err));

		})
	}

	matchingInterval = setInterval(function() {
		if (timing > 1 && timing % 10 == 0) {
			if (timing > 50) {
				alert("최대 검색 거리에 도달하여 더이상 검색 할 수 없습니다.\n매칭을 종료하겠습니다.")
				stopMatcing(matchCode);
			}
			if (confirm(`'${km}'km 이내에 매칭을 완료했습니다.
                \n더 넓은 범위를 탐색하시겠습니까?`)) {
				km++;
			} else {
				stopMatcing(matchCode);
			}
		}
		timing++;

		$.ajax({
			method: "post",
			url: "/ajax/callNavi",
			contentType: "application/json",
			data: JSON.stringify(sendData),
		})
			.done(result => {
				result.forEach(data => {
					const stationlat = data.y;
					const stationlon = data.x;

					let distanceInKm = getDistanceFromLatLonInKm(lon, lat, stationlon, stationlat);

					removeMarkers();
					if (distanceInKm <= km) {
						createMarker(stationlat, stationlon);
					}
				})
			})
			.fail(err => console.log(err));

	}, 5000);
}

//거리 계산
function getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) {
	let R = 6371; // Radius of the earth in km
	let dLat = deg2rad(lat2 - lat1);  // deg2rad below
	let dLon = deg2rad(lon2 - lon1);
	let a =
		Math.sin(dLat / 2) * Math.sin(dLat / 2) +
		Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
		Math.sin(dLon / 2) * Math.sin(dLon / 2);
	let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	let d = R * c; // Distance in km
	return d;
}
function deg2rad(deg) {
	return deg * (Math.PI / 180)
}

//매칭종료
function stopMatcing(matchCode) {
	console.log("stop : " + matchCode)
	$.ajax({
		method: "get",
		contentType: "application/x-www-form-urlencode",
		url: "/ajax/endMatch?matchCode=" + matchCode,
	})
		.done(result => {
			console.log(result);
			if (result == "success") {
				alert("매칭을 종료합니다.");
				clearInterval(matchingInterval);
			} else {
				alert("종료할 매칭이 없습니다.");
			}
		})
		.fail(err => {
			console.log(err);
			alert("매칭을 종료하지 못했습니다.\n다시 시도해 주십시오.");
		});
}

//마커 생성
function createMarker(lat, lng) {
	const markerPosition = new kakao.maps.LatLng(lat, lng);
	var imageSrc = '/assets/main/img/navi/logo_noText_noBack.png', // 마커이미지의 주소입니다    
		imageSize = new kakao.maps.Size(50, 52), // 마커이미지의 크기입니다
		imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
	const marker = new kakao.maps.Marker({
		position: markerPosition,
		image: markerImage,
		clickable: true
	});
	const mark = {
		marker: marker,
		add: 1
	}
	console.log(mark);
	clickMarkerEvent(marker);

	markers.push(mark);
	marker.setMap(map);
}

//마커 제거
function removeMarkers() {

	markers.forEach(mark => {
		const marker = mark.marker;
		if (mark.add == 1) {
			//console.log(mark.add);
			marker.setMap(null); // 지도에서 제거
		}

	});
}

//마커 이벤트 등록
function clickMarkerEvent(marker) {
	// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
	let iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

	// 인포윈도우를 생성합니다
	let infowindow = new kakao.maps.InfoWindow({
		content: iwContent,
		removable: iwRemoveable
	});

	// 마커에 클릭이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		// 마커 위에 인포윈도우를 표시합니다
		infowindow.open(map, marker);
	});
}