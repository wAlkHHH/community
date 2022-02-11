package com.walkhhh.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-11 8:21
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page){
        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for(int i = 1; i <=  3; i++){
            if(page - i > 0){
                pages.add(0, page - i);
            }
            if(page + i <= totalPage){
                pages.add(page + i);
            }
        }

        //是否展示前面的页
        if( page == 1){
            this.showPrevious = false;
        }else{
            this.showPrevious = true;
        }

        //是否展示后面的页
        if( page == totalPage){
            this.showNext = false;
        }else{
            this.showNext = true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }


        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }


    }
}
