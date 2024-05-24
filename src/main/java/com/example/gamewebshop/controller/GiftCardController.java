package com.example.gamewebshop.controller;

import com.example.gamewebshop.dao.ApiResponseDTO;
import com.example.gamewebshop.dao.GiftCardDAO;
import com.example.gamewebshop.dao.UserDAO;
import com.example.gamewebshop.dao.UserGiftCardDAO;
import com.example.gamewebshop.dto.GiftCardDTO;
import com.example.gamewebshop.dto.MiniGiftCardDTO;
import com.example.gamewebshop.dto.UserGiftCardDTO;
import com.example.gamewebshop.models.GiftCard;
import com.example.gamewebshop.models.UserGiftCard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://s1148232.student.inf-hsleiden.nl:18232"})
@RequestMapping("/giftCards")
public class GiftCardController {

    private final GiftCardDAO giftCardDAO;
    private final UserGiftCardDAO userGiftCardDAO;
    private final UserDAO userDAO;

    public GiftCardController(GiftCardDAO giftCardDAO,UserGiftCardDAO userGiftCardDAO,UserDAO userDAO) {
        this.giftCardDAO = giftCardDAO;
        this.userGiftCardDAO = userGiftCardDAO;
        this.userDAO = userDAO;
    }

    @GetMapping
    public ResponseEntity<List<GiftCard>> getAllActiveGiftCards(){
        return ResponseEntity.ok(this.giftCardDAO.getAllActiveGiftCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftCard> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(this.giftCardDAO.getGiftCardById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> create(@RequestBody GiftCard giftCard){
        this.giftCardDAO.createGiftCard(giftCard);
        return new ResponseEntity<>(new ApiResponseDTO("Gift card has been added",HttpStatus.OK.value()),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody GiftCardDTO giftCardDTO){
        this.giftCardDAO.updateGiftCard(giftCardDTO, id);
        return ResponseEntity.ok("Updated Gift Card with id" + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> deleteById(@PathVariable Long id){
        this.giftCardDAO.deleteById(id);
        return new ResponseEntity<> (new ApiResponseDTO("GiftCard deleted with id " + id,HttpStatus.OK.value()),HttpStatus.OK);
    }
    @PostMapping("/{email}/{giftCardId}")
    public ResponseEntity<ApiResponseDTO> sendGiftCard(@PathVariable String email, @PathVariable Long giftCardId){
        userGiftCardDAO.saveUserGiftCard(email,giftCardId);
        return new ResponseEntity<>(new ApiResponseDTO("Gift Card sent to user with email : " + email,HttpStatus.OK.value()),HttpStatus.OK);
    }
    @GetMapping("/getAllGiftCardSentByUser")
    public List<UserGiftCard> getAllGiftCardSentByUser(Principal principal){
        return userGiftCardDAO.getAllGiftCardsSentByUser(userDAO.getUserByEmail(principal.getName()).getId());
    }
    @GetMapping("/getAllGiftCardsReceivedByUser")
    public List<UserGiftCard> getAllGiftCardsReceivedByUser(Principal principal){
        return userGiftCardDAO.getAllGiftCardsReceivedByUser(userDAO.getUserByEmail(principal.getName()).getId());
    }
    @GetMapping("/mini")
    public ResponseEntity<List<MiniGiftCardDTO>> getAllActiveGiftCardsFoDropDown(Principal principal){
        return ResponseEntity.ok(this.userGiftCardDAO.getGiftCardsDropDown(userDAO.getUserByEmail(principal.getName()).getId()));
    }
    @GetMapping("/users")
    public List<UserGiftCardDTO> getAllUserGiftCards(){
        return userGiftCardDAO.getAllUserGiftCars();
    }
}
