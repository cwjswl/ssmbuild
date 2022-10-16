package com.xiao.controller;

import com.xiao.pojo.Books;
import com.xiao.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    //Controller层调用Service层
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    //1、查询全部书籍，并返回到一个书籍展示界面
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper(){
        return "addBook";
    }
    //添加书籍的请求
    @RequestMapping("/addBook")
    public String addBook(Books books){
        System.out.println(books);
        bookService.addBook(books);
        return "redirect:/book/allBook";//执行完后，重定向跳转到书籍展示界面，即allBook界面
    }

        //跳转到修改书籍页面
        @RequestMapping("/toUpdateBook")
        public String toUpdateBook(Model model, int id) {
            Books books = bookService.queryBook(id);
            System.out.println(books);
            model.addAttribute("book", books);
            return "updateBook";//跳转到updateBook.jsp界面
        }
        //修改书籍
        @RequestMapping("/updateBook")
        public String updateBook(Books books) {
            bookService.updateBook(books);
            return "redirect:/book/allBook";//修改后重定向至书籍展示界面，即allBook.jsp界面
        }

    //删除书籍
    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        bookService.deleteBook(id);
        return "redirect:/book/allBook";//删除后重定向至书籍展示界面，即allBook.jsp界面
    }

    //搜索查询书籍
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName,Model model){
            System.out.println("要查询的书籍:"+queryBookName);
            //如果查询的数据存在空格，则优化
            Books books = bookService.queryBookByName(queryBookName);
            List<Books> list = new ArrayList<Books>();
            list.add(books);
            //如果没有查出来书籍，则返回全部书籍列表
            if (books==null){
                list = bookService.queryAllBook();
                model.addAttribute("error", "没有找到本书！");
            }
            model.addAttribute("list", list);
        return "allBook";
    }
}
