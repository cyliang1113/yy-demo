package cn.leo.xback.common;

import java.util.List;

/**
 * 分页
 */
public class Page<T> {
	/**
	 * 当前页
	 */
	private Long currentPage = Long.valueOf(1);
	/**
	 * 每页数查询数量
	 */
	private Long pageSize = Constant.DEFAULT_PAGE_SIZE;
	/**
	 * 记录总数
	 */
	private Long total;
	/**
	 * 记录集
	 */
	private List<T> items;

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	
	public void setPage(Long page){
		this.currentPage = page;
	}

	public void setRows(Long rows){
		this.pageSize = rows;
	}
}
