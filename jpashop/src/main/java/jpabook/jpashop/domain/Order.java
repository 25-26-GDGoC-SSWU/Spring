package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간

    private OrderStatus status; //주문상태 [ORDER, CANCEL]
    
    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member = member; //Order 객체에 Member 연결
        member.getOrders().add(this); //Member 객체에 Order 연결
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){ //...은 여러 개를 넘길 수 있다는 뜻
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER); //주문 상태로 전환
        order.setOrderDate(LocalDateTime.now()); //주문 시간
        return order;
    }

    //==비즈니스 로직==//
    //주문 취소
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP) { //이미 배송 완료 상태일 때
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem: orderItems){ //2개 이상 주문했을 때 각각을 취소
            orderItem.cancel(); //재고 수량 원복
        }
    }

    //==조회 로직==//
    //전체 주문 가격 조회
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem: orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}