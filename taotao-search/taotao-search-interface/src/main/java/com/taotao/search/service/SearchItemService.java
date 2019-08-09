package com.taotao.search.service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

public interface SearchItemService {
    TaotaoResult importAllItems();
    SearchResult search(String queryString, int page, int rows) throws Exception;

    void addDocument(SearchItem searchItem);
}
