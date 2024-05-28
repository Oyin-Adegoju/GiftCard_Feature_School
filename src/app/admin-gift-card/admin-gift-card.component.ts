import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GiftcardService } from '../services/giftcard.service';
import { GiftCard } from '../models/giftcard.model';
import { UserGiftCard } from '../models/usergiftcard.model';

@Component({
  selector: 'app-admin-gift-card',
  templateUrl: './admin-gift-card.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  styleUrl: './admin-gift-card.component.scss'
})
export class AdminGiftCardComponent implements OnInit{
  public bestelForm: FormGroup;
  public giftCard: GiftCard;
  public giftCards: GiftCard[] = new Array<GiftCard>();
  public loadingGiftCards: boolean = true;
  public userGiftCards: UserGiftCard[] = new Array<UserGiftCard>();


  constructor(private giftCardService: GiftcardService, private router: Router, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.bestelForm = this.fb.group({
      name: ['', [Validators.required]],
      amount: ['', [Validators.required]],
      image: [''],
    });
    this.giftCardService
      .getGiftCards()
      .subscribe((giftCards: GiftCard[]) => {
        this.loadingGiftCards = false;
        this.giftCards = giftCards;
        console.log('gift cards ',this.giftCards);
    });
    console.log('gift cards bad me ',this.giftCards);
    this.giftCardService
      .getAllUsersGiftCards()
      .subscribe((userGiftCards: UserGiftCard[]) => {
        this.userGiftCards = userGiftCards;
        console.log('user gift cards ',this.userGiftCards);
    });
    console.log('user gift cards bad me ',this.giftCards);

  }

  public onSubmit() {
    const formData = this.bestelForm.value;
    console.log('bestelFom ',formData);
    this.giftCard = {
      id: formData.id,
      name: formData.name,
      amount: formData.amount,
      image: formData.image,
      cardCode:''
    };
    console.log('gift cards bad me ',this.giftCards);
    this.giftCardService.createGiftCard(this.giftCard).subscribe(
      (result) => {
        console.log('Gift Card added successfully:', result);
        alert(result.message);
        this.router.navigateByUrl('/admin-gift-cards');
        window.location.reload();
      },
      (error) => {
        console.error('Failed to add gift card:', error);
        alert('Failed to add Gift Card');
        window.location.reload();
      }
    );

}
public deleteCard(id: number){
  this.giftCardService.deleteGiftCardById(id).subscribe(
    (result) => {
      console.log('Gift Card deleted successfully:', result);
      alert(result.message);
      window.location.reload();
    },
    (error) => {
      console.error('Failed to delete gift card:', error);
      alert('Failed to add Gift Card');
      window.location.reload();
    }
  );
}

}
