import { Component, OnInit } from '@angular/core';
import { CartService } from "../services/cart.service";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import { Router } from "@angular/router";
import { Product } from '../models/product.model';
import { Order } from '../models/order.model';
import { IDropdownSettings, NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { GiftcardService } from '../services/giftcard.service';
import { MiniGiftCard } from '../models/minigiftcard.model';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    NgMultiSelectDropDownModule
  ],
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  public bestelForm: FormGroup;
  public products_in_cart: Product[];
  public order: Order;
  dropdownList: any[] = [];
  selectedItems: MiniGiftCard[] = [];
  public miniGiftCards: MiniGiftCard[] = new Array<MiniGiftCard>();
  dropdownSettings:IDropdownSettings;
  selectedIds: any[] = [];

  constructor(private cartService: CartService, private giftCardService: GiftcardService,private router: Router, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.products_in_cart = this.cartService.allProductsInCart();
    this.bestelForm = this.fb.group({
      name: ['', [Validators.required]],
      infix: [''],
      lastName: ['', [Validators.required]],
      zipCode: ['', [Validators.required]],
      houseNumber: ['', [Validators.required, Validators.maxLength(5)]],
      notes: [''],
    });
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'cardCode',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
    this.giftCardService.getMiniGiftCards()
    .subscribe((miniGiftCards: MiniGiftCard[]) => {
        this.miniGiftCards = miniGiftCards;
    });
  }
  onItemSelect(item: any) {
    console.log(item);
    this.selectedItems.push(item);
    this.selectedIds.push(item.id);
  }
  onSelectAll(items: any) {
    console.log(items);
    this.selectedIds.length=0;
    this.selectedItems.length=0;
    this.selectedItems.push(items);
    for(let item of items){
      this.selectedIds.push(item.id);
    }
    
  }

  public clearCart() {
    this.cartService.clearCart();
  }

  public onSubmit() {
      const formData = this.bestelForm.value;
      this.order = {
        id: formData.id,
        name: formData.name,
        infix: formData.infix,
        last_name: formData.lastName,
        zipcode: formData.zipCode,
        houseNumber: formData.houseNumber,
        notes: formData.notes,
        orderDate: formData.orderDatum,
        products: this.products_in_cart,
        giftCardIds:this.selectedIds
      };

      this.cartService.addOrder(this.order).subscribe(
        (result) => {
          console.log('Order added successfully:', result);
          localStorage.setItem("remainingAmount",result.remainingAmount.toString());
          this.clearCart();
          this.router.navigateByUrl('/paymentsuccessful');
        },
        (error) => {
          console.error('Failed to add order:', error);
        }
      );

  }
}
