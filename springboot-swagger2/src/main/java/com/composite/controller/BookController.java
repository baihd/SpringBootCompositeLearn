package com.composite.controller;

import com.composite.entity.Book;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * 用户创建某本图书 POST    /books/
 * 用户修改对某本图书    PUT /books/:id/
 * 用户删除对某本图书    DELETE  /books/:id/
 * 用户获取所有的图书 GET /books
 * 用户获取某一图书  GET /Books/:id
 */
@RestController
@RequestMapping(value = "/books")
public class BookController {
    Map<Long, Book> bookMap = Collections.synchronizedMap(new HashMap<Long, Book>());

    @ApiOperation(value = "获取图书列表", notes = "获取图书列表")
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<Book> getBook() {
        List<Book> books = new ArrayList<>(bookMap.values());
        return books;
    }

    @ApiOperation(value = "创建图书", notes = "创建图书")
    @ApiImplicitParam(name = "book", value = "图书详细实体", dataType = "Book")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postBook(@RequestBody Book book) {
        bookMap.put(book.getId(), book);
        return "success";
    }

    @ApiOperation(value = "获图书细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable Long id) {
        return bookMap.get(id);
    }

    @ApiOperation(value = "更新信息", notes = "根据url的id来指定更新图书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "book", value = "图书实体book", dataType = "Book")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody Book book) {
        Book book1 = bookMap.get(id);
        book1.setName(book.getName());
        book1.setPrice(book.getPrice());
        bookMap.put(id, book1);
        return "success";
    }

    @ApiOperation(value = "删除图书", notes = "根据url的id来指定删除图书")
    @ApiImplicitParam(name = "id", value = "图书ID", dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        bookMap.remove(id);
        return "success";
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String jsonTest() {
        return " hi you!";
    }


}
