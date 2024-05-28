import {Component, OnInit} from '@angular/core';
import {Product} from "../models/product.model";
import {UserService} from "../services/user.service";
import {User} from "../models/user.model";
import {CommonModule} from "@angular/common";
import {RouterLink} from "@angular/router";
import { GiftcardService } from '../services/giftcard.service';
import { GiftCard } from '../models/giftcard.model';
import { UserGiftCard } from '../models/usergiftcard.model';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit {
  public user: User;
  public sentGiftCards: UserGiftCard[] = new Array<UserGiftCard>();
  public recievedGiftCards: UserGiftCard[] = new Array<UserGiftCard>();

  constructor(private userService: UserService,private giftCardService: GiftcardService) {
  }

  ngOnInit(): void {
    this.userService
      .getUserByEmail()
      .subscribe((user: User) => {
        this.user = user;
      });

      this.giftCardService
      .getGiftCardsSendByUser()
      .subscribe((giftCards: UserGiftCard[]) => {
        this.sentGiftCards = giftCards;
        console.log('sentGiftCards ',this.sentGiftCards);
     });
     console.log('sentGiftCards bad me ',this.sentGiftCards);
     
     this.giftCardService
      .getGiftCardsReciviedByUser()
      .subscribe((recievedGiftCards: UserGiftCard[]) => {
        this.recievedGiftCards = recievedGiftCards;
        console.log('gift cards received ',this.recievedGiftCards);
     });
     console.log('gift cards received bad me ',this.recievedGiftCards);
  }

}
