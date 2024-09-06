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
let interval;           //산책할 때 쓸 반복실행
let overlays = [];      //지도에 존재하는 선목록
let map;

//지도 설정
function setMap(mapWrap = "map") {
    let mapContainer = document.getElementById(mapWrap); // 지도를 표시할 div 
    let mapOption = {
        center: new kakao.maps.LatLng(35.8691089, 128.593387), // 지도의 중심좌표(예담)
        level: 5
        // 지도의 확대 레벨 
    };

    map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    //그리기 도구 설정
    let drawOptions = { // Drawing Manager를 생성할 때 사용할 옵션입니다
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
};

//실시간 경로그리기(산책시작)
function startWalking(code = 'test01', used = 'puppy') {
    //used => puppy, schedule, journal;
    let turn = 0;

    interval = setInterval(function () {
        // HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
        if (navigator.geolocation) {
            // GeoLocation을 이용해서 접속 위치를 얻어옵니다
            navigator.geolocation.getCurrentPosition(function (position) {

                let lat = position.coords.latitude; // 위도(세로)
                let lon = position.coords.longitude; // 경도(가로)

                let sendData = setData(turn, code, used, lon, lat);
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
                        datas.forEach(data => {
                            locations.push({
                                turnNo: data.turnNo,
                                x: data.x,
                                y: data.y
                            });
                        });

                        //선 삭제
                        removeOverlays();

                        //선그리기
                        drawPolylineNow(locations);
                    })
                    .fail(err => console.log(err));
            });

        } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

            alert('위치 정보를 가져올 수 없습니다.');
        }
    }, 10000);
}

function setData(turnNo, code, used, x, y) {
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
    } else {
        data.journalCode = code;
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
        strokeWeight: 3
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
    clearInterval(interval);
}