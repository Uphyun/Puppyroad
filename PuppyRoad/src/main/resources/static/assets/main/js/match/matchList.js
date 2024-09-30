/**
 * matchList
 */

'use strict';

// Comment editor

const commentEditor = document.querySelector('.comment-editor');

if (commentEditor) {
  new Quill(commentEditor, {
    modules: {
      toolbar: '.comment-toolbar'
    },
    placeholder: 'Write a Comment...',
    theme: 'snow'
  });
}

// Datatable (jquery)

$(function () {
  let borderColor, bodyBg, headingColor;

  if (isDarkStyle) {
    borderColor = config.colors_dark.borderColor;
    bodyBg = config.colors_dark.bodyBg;
    headingColor = config.colors_dark.headingColor;
  } else {
    borderColor = config.colors.borderColor;
    bodyBg = config.colors.bodyBg;
    headingColor = config.colors.headingColor;
  }

  // Variable declaration for category list table
  var dt_category_list_table = $('.datatables-category-list');

  //select2 for dropdowns in offcanvas

  var select2 = $('.select2');
  if (select2.length) {
    select2.each(function () {
      var $this = $(this);
      $this.wrap('<div class="position-relative"></div>').select2({
        dropdownParent: $this.parent(),
        placeholder: $this.data('placeholder') //for dynamic placeholder
      });
    });
  }

  // 테이터 테이블

  if (dt_category_list_table.length) {
    var dt_category = dt_category_list_table.DataTable({
      //processing: true,
      //serverSide: true,
      ajax: { 
		url: "/user/matchListAjax",
        type: 'GET',
        dataSrc: ''
      },
      columns: [
        { data: 'bulletinNo' },
        { data: 'title' },
        { data: 'walkPlaceAddress' },
        { data: 'matchingState' },
        { data: 'writer' },
        { data: 'writingTime' }
      ],

      columnDefs: [
        {
          // 번호
          targets: 0, // 몇번째 컬럼
          width: '5%',
          //responsivePriority: 1, // 반응형에서 사용되는 순서 값
          render: function (data, type, full, meta) { // data: 데이터
            var $bulletinNo = full['bulletinNo'];
            return '<div class="">' + $bulletinNo + '</div>';
          }
        },
        {
          // 제목
          targets: 1,
          width: '40%',
          //responsivePriority: 2,
          render: function (data, type, full, meta) {
            var $title = full['title'];
            return '<div class="text-nowrap">' + $title + '</div>';
          }
        },
        {
          // 장소
          targets: 2,
          //responsivePriority: 3,
          render: function (data, type, full, meta) {
            var $walkPlaceAddress = full['walkPlaceAddress'];
            return '<div class="text-sm-end">' + $walkPlaceAddress + '</div>';
          }
        },
        {
          // 매칭상태
          targets: 3,
          //responsivePriority: 4,
          render: function (data, type, full, meta) {
            var $matchingState = full['matchingState'];
            
            if($matchingState == 1){
				$matchingState = '매칭중';
			} else if($matchingState == 2){
				$matchingState = '매칭예약';
			} else if($matchingState == 3){
				$matchingState = '매칭완료';
			} else {
				$matchingState = '비활성화';
			}
            
            return '<div class="text-sm-center">' + $matchingState + '</div>';
          }
        },
        {
          // 작성자
          targets: 4,
          //responsivePriority: 5,
          render: function (data, type, full, meta) {
            var $writer = full['writer'];
            return '<div class="text-sm-end">' + $writer + '</div>';
          }
        },
        {
          // 작성시간
          targets: 5,
          //responsivePriority: 6,
          render: function (data, type, full, meta) {
            var $writingTime = full['writingTime'];
            return '<div class="text-sm-center">' + moment($writingTime).format('YYYY년MM월DD일') + '</div>';
          }
        },
      ],
      
      order: [0, 'desc'], //[0]번째컬럼 내림차순
      dom:
        `<'card-header d-flex flex-wrap py-3 flex-column flex-sm-row'
        <'filter3 col-md-6'>
        <'d-flex justify-content-center justify-content-md-end align-items-baseline'<'dt-action-buttons d-flex justify-content-center flex-md-row align-items-baseline'lB>>
        >t
        <'row mx-1'
        <'col-sm-6 col-md-4'>
        <'d-flex justify-content-center'p>
        <'col-sm-6 col-md-4'>
        >`,
      lengthChange: false,
      lengthMenu: [10], // 페이징  10
      language: {
        sLengthMenu: '_MENU_',
        search: '',
        searchPlaceholder: '검색',
        paginate: {
          next: '<i class="ti ti-chevron-right ti-sm"></i>',
          previous: '<i class="ti ti-chevron-left ti-sm"></i>'
        }
      },
      // 작성버튼
      buttons: [
        {
          text: '<i class="ti ti-plus ti-xs me-0 me-sm-2"></i><span class="d-none d-sm-inline-block">작성하기</span>',
          className: 'add-new btn btn-primary ms-2 waves-effect waves-light',
          attr: {
            'onclick': "location.href='/user/matchInsert'"
          }
        }
      ],
    });//dataTables 끝
    $('.dt-action-buttons').addClass('pt-0');
    $('.dataTables_filter').addClass('me-3 mb-sm-6 mb-0 ps-0');
    
    // dataTables row 클릭이벤트
    $('.datatables-category-list').on('click', 'tbody tr',function () {
	    var row = dt_category.row($(this)).data();
		location.href = '/user/matchInfo?bulletinNo=' + row.bulletinNo;
		
	});
	
	
	$('.filter3').append($('#cityFilter'));
	
    // dataTables 검색필터 추가
    $('#categoryFilter').on('change', function() {
    	dt_category.columns(2).search(this.value).draw(); // 3번째 col인 지역
	});
	dt_category.draw();
  }
  // Filter form control to default size
  // ? setTimeout used for multilingual table initialization
  setTimeout(() => {
    $('.dataTables_filter .form-control').removeClass('form-control-sm');
    $('.dataTables_filter .form-control').addClass('ms-0');
    $('.dataTables_length .form-select').removeClass('form-select-sm');
    $('.dataTables_length .form-select').addClass('ms-0');
  }, 300);
});


//For form validation
(function () {
  const eCommerceCategoryListForm = document.getElementById('eCommerceCategoryListForm');

  //Add New customer Form Validation
  const fv = FormValidation.formValidation(eCommerceCategoryListForm, {
    fields: {
      categoryTitle: {
        validators: {
          notEmpty: {
            message: 'Please enter category title'
          }
        }
      },
      slug: {
        validators: {
          notEmpty: {
            message: 'Please enter slug'
          }
        }
      }
    },
    plugins: {
      trigger: new FormValidation.plugins.Trigger(),
      bootstrap5: new FormValidation.plugins.Bootstrap5({
        // Use this for enabling/changing valid/invalid class
        eleValidClass: 'is-valid',
        rowSelector: function (field, ele) {
          // field is the field name & ele is the field element
          return '.mb-6';
        }
      }),
      submitButton: new FormValidation.plugins.SubmitButton(),
      // Submit the form when all fields are valid
      // defaultSubmit: new FormValidation.plugins.DefaultSubmit(),
      autoFocus: new FormValidation.plugins.AutoFocus()
    }
  });
})();
