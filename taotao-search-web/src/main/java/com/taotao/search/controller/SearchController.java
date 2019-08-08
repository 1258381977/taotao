package com.taotao.search.controller;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;


    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString,
                         @RequestParam(defaultValue = "1") Integer page, Model model) throws Exception {
        byte[] bytes = queryString.getBytes("ISO-8859-1");
        String s=new String(bytes,"UTF-8");
        SearchResult result = searchItemService.search(s, page, ITEM_ROWS);
        //传递给页面
        model.addAttribute("query", s);
        model.addAttribute("totalPages", result.getPageCount());
        model.addAttribute("itemList", result.getItemList());
        model.addAttribute("page", page);
        return "search";
    }
}