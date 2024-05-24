package com.example.gamewebshop.dao;

import com.example.gamewebshop.dto.OrderResponseDTO;
import com.example.gamewebshop.models.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class OrderDAO {
    private final OrderRepository orderRepository;
    private final UserDAO userDAO;
    private final UserGiftCardDAO userGiftCardDAO;

    public OrderDAO(OrderRepository orderRepository, UserDAO userDAO,UserGiftCardDAO userGiftCardDAO) {
        this.orderRepository = orderRepository;
        this.userDAO = userDAO;
        this.userGiftCardDAO = userGiftCardDAO;
    }

    public List<PlacedOrder> getAllOrders(){
        return  this.orderRepository.findAll();
    }


    @Transactional
    public void createOrder(PlacedOrder placedOrder){
        this.userDAO.save(placedOrder.getUser() );

        this.orderRepository.save(placedOrder);

    }


    @Transactional
    public OrderResponseDTO saveOrderWithProducts(PlacedOrder order, String userEmail) {
        CustomUser user = userDAO.getUserByEmail(userEmail);
        int totalProducts = order.getProducts().size();
        order.setTotalProducts(totalProducts);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        double totalAmount=0;
        double giftCardAmount=0;
        for(Product product:order.getProducts()){
            totalAmount+= product.getPrice().doubleValue();
        }
        List<UserGiftCard> userGiftCards = userGiftCardDAO.getAllUserGiftCardsByGiftCardIds(order.getGiftCardIds());
        if(Objects.isNull(userGiftCards) || userGiftCards.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilized gift cards doesn't belong this user");
        }
        for(UserGiftCard userGiftCard : userGiftCards){
            if(Objects.nonNull(userGiftCard.getGiftCard())){
                giftCardAmount+=userGiftCard.getGiftCard().getAmount();
            }
        }
        if(giftCardAmount>totalAmount){
            long userGiftCardsSize = userGiftCards.size();
            double usedAmountPerGiftCard = totalAmount/userGiftCardsSize;
            for(UserGiftCard userGiftCard : userGiftCards){
                userGiftCard.getGiftCard().setAmount(userGiftCard.getGiftCard().getAmount()-usedAmountPerGiftCard);
            }

        }
        order.setTotalAmount(totalAmount);
        order.setPaidAmountByGiftCard(giftCardAmount);
        orderRepository.save(order);
        userGiftCardDAO.removeUsedGiftCards(userGiftCards.stream().map(ug->ug.getGiftCard().getId()).toList());
        OrderResponseDTO response = new OrderResponseDTO();
        response.setTotalAmount(totalAmount);
        response.setUsedAmount(giftCardAmount);
        response.setRemainingAmount(totalAmount-giftCardAmount);
        response.setMessage("Order created successfully");
        return response;
    }

    public List<PlacedOrder> getOrdersByUserId(long userId){
        Optional<List<PlacedOrder>> orderList = this.orderRepository.findByUserId(userId);
        if (orderList.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No products found with that category id"
            );
        }
        return orderList.get();
    }

}
