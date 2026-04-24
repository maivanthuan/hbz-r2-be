package com.hbz.hbzr2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
private Long id;
private String content;
private String bookName;
private String authorName;
public ReviewDTO() {}
public ReviewDTO(Long id, String content, String bookName, String authorName) {
    this.id = id;
    this.content = content;
    this.bookName = bookName;
    this.authorName = authorName;
}
}
