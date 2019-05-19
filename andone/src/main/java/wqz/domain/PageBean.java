package wqz.domain;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageBean<T>{
	private List<T> pageData;
	private int totalPage;
	private int currentPage;
	public PageBean(Page<T> page){
		this.pageData = page.getContent();
		this.totalPage = page.getTotalPages();
		this.currentPage = page.getNumber();
	}
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}
