package com.puppyroad.app.main.service;

import lombok.Data;

@Data
public class PageDTO {
	private int page;             			// 현재 페이지 번호
    private double recordSize;       		// 페이지당 출력할 데이터 개수
    private int pageSize;         			// 화면 하단에 출력할 페이지 사이즈
    private int totalCnt;		  			// 총 데이터 수
    private int realEnd;					// 최종 페이지 수
    private int startPage, endPage;			// 목록중 첫페이지, 끝페이지
    private boolean prev, next;   			// 전 후 페이지 판단
    
    private String keyword;       			// 검색 키워드
    private String searchType;    			// 검색 유형
    
    public PageDTO() {
    	this.pageSize = 10;
    };

    public PageDTO(int page, double recordSize, int totalCnt) {
    	this.pageSize = 10;
        this.page = page;
        this.recordSize = recordSize;
        this.totalCnt = totalCnt;
        
        this.endPage = (int) (Math.ceil(page/this.recordSize) * this.pageSize);
        this.startPage = this.endPage - (this.pageSize - 1);
        this.realEnd = (int) Math.ceil(this.totalCnt/this.recordSize);
        
        this.prev = this.startPage > 1;
        this.next = this.endPage < this.realEnd;
    }

    public int getOffset() {
        return (int)((page - 1) * recordSize);
    }
}
