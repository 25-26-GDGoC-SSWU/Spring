package jpabook.jpashop.Controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id;

    //상품의 공통 속성
    private String name;
    private int price;
    private int stockQuantity;

    //책의 속성
    private String author;
    private String isbn;
}
