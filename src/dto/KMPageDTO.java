package dto;

public class KMPageDTO {

	private int page;
	private int maxPage;
	private int startPage;
	private int endPage;
	private int listCount;
	private int limit;
	private String sCategory;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getsCategory() {
		return sCategory;
	}
	public void setsCategory(String sCategory) {
		this.sCategory = sCategory;
	}
	@Override
	public String toString() {
		return "KMPageDTO [page=" + page + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", listCount=" + listCount + ", limit=" + limit + ", sCategory=" + sCategory + "]";
	}
	public KMPageDTO(int page, int maxPage, int startPage, int endPage, int listCount, int limit, String sCategory) {
		super();
		this.page = page;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.listCount = listCount;
		this.limit = limit;
		this.sCategory = sCategory;
	}
	public KMPageDTO() {
		super();
	}
	

	
}
