package com.xiao.dao;

import com.xiao.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
        //增加一个Book
        int addBook(Books book);
        //更新一个Book
        int updateBook(Books book);
        //根据ID删除一个Book
        int deleteBook(@Param("bookID") int id);
        //根据ID查询一个Book
        Books queryBook(@Param("bookID")int id);
        //查询全部的Book，返回集合List
        List<Books> queryAllBook();

        //根据name查询,返回一个Book
        Books queryBookByName(@Param("bookName")String bookName);

}

