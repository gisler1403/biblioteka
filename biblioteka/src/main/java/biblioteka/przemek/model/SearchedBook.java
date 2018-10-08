package biblioteka.przemek.model;

import java.util.LinkedHashMap;

public class SearchedBook {

	private String searchedName;

	private String category;

	private LinkedHashMap<String, String> categoryList;

	public String getSearchedName() {
		return searchedName;
	}

	public void setSearchedName(String searchedName) {
		this.searchedName = searchedName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LinkedHashMap<String, String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(LinkedHashMap<String, String> categoryList) {
		this.categoryList = categoryList;
	}

}
