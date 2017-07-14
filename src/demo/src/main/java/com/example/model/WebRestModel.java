package com.example.model;

import java.util.List;


/**
 * 一括登録結果表示画面のFormModel
 */
public class WebRestModel {

	/** 実行結果コード */
	private Boolean status;

	/** 実行結果内容 */
	private List<String> resultList;

	/**
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status セットする status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return resultList
	 */
	public List<String> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
	}

}
