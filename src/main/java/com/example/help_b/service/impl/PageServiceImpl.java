package com.example.help_b.service.impl;

import com.example.help_b.model.Page;
import com.example.help_b.service.PageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class PageServiceImpl implements PageService {
    @Value("${page.show.maxsize}")
    int pageShowMaxsize;
    @Override
    public Page wrestleWithPage(Integer currentPage,Integer sum,Integer size) {
        Page pageInfo=new Page();
        pageInfo.setCurrentPage(currentPage);
        pageInfo.setLastPage(sum%size==0?sum/size:sum/size+1);

        LinkedList<Integer> linkedList=new LinkedList<>();
        linkedList.add(currentPage);
        int totalPage;
        if(sum%size==0){
            totalPage = sum/size;
        }else{
            totalPage=sum/size+1;
        }
        for(int i=1;i<=pageShowMaxsize/2;i++){
            if(currentPage-i>0){
                linkedList.addFirst(currentPage-i);
            }
            if(currentPage+i<=totalPage){
                linkedList.addLast(currentPage+i);
            }
        }
        pageInfo.setPages(linkedList);
        return pageInfo;
    }
}
