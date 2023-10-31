package com.leafbug.todolist.model;

public class PageHandler {
	private int page; // ���� ������
	private int pageSize; // �� ������ ũ��
	private int totalCnt; // �� �Խù� ����
	private int naviSize = 10; // ������ �׺���̼��� ũ��
	private int totalPage; // ��ü �������� ����

	private int beginPage; // �׺���̼��� ù��° ������
	private int endPage; // �׺���̼��� ������ ������
	private boolean showPrev; // ���� �������� �̵��ϴ� ��ũ�� ������ �������� ����
	private boolean showNext; // ���� �������� �̵��ϴ� ��ũ�� ������ �������� ����
	
	private SearchCondition sc;
	
	private void doPaging(int totalCnt, SearchCondition sc) {
		this.totalCnt = totalCnt;
		totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize()); // �ø�
		beginPage = (sc.getPage()-1)/naviSize *naviSize + 1;
		endPage = Math.min(beginPage + naviSize-1, totalPage);
		showPrev = beginPage != 1;
		showNext = endPage != totalPage;
	}
	
	public PageHandler(int totalCnt, SearchCondition sc) {
		this.totalCnt=totalCnt;		
		this.sc = sc;
		doPaging(totalCnt, sc);
	}
	
	public PageHandler() {
		super();
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getNaviSize() {
		return naviSize;
	}
	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isShowPrev() {
		return showPrev;
	}
	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}
	public boolean isShowNext() {
		return showNext;
	}
	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}
	
	public SearchCondition getSc() {
		return sc;
	}

	public void setSc(SearchCondition sc) {
		this.sc = sc;
	}

	@Override
	public String toString() {
		return "PageHandler [page=" + page + ", pageSize=" + pageSize + ", totalCnt=" + totalCnt + ", naviSize=" + naviSize
				+ ", totalPage=" + totalPage + ", beginPage=" + beginPage + ", endPage=" + endPage + ", showPrev=" + showPrev
				+ ", showNext=" + showNext + ", sc=" + sc + "]";
	}

	
}
