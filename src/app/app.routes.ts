import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {authGuard} from './auth/auth.guard';
import {LoginComponent} from './auth/login/login.component';
import {RegisterComponent} from './auth/register/register.component';
import {ProductsComponent} from "./products/products.component";
import {CartComponent} from "./cart/cart.component";
import {ProductDetailComponent} from './products/product-detail/product-detail.component';
import {ProfileComponent} from "./profile/profile.component";
import {ProfileUpdateComponent} from "./profile/profile-update/profile-update.component";
import {OrderComponent} from "./order/order.component";
import {PaymentSuccessfulComponent} from "./order/payment-successful/payment-successful.component";
import { OrderHistoryComponent } from './profile/order-history/order-history.component';
import { GiftCardsComponent } from './gift-cards/gift-cards.component';
import { GiftCardDetailComponent } from './gift-cards/gift-card-detail/gift-card-detail.component';
import { AdminGiftCardComponent } from './admin-gift-card/admin-gift-card.component';
import { AdminLoginComponent } from './auth/admin-login/admin-login.component';


export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'auth/login', component: LoginComponent},
  {path: 'auth/register', component: RegisterComponent},
  {path: 'products', component: ProductsComponent},
  {path: 'cart', component: CartComponent},
  {path: 'products/:id', component: ProductDetailComponent},
  {path: 'profile', component: ProfileComponent, canActivate: [authGuard]},
  {path: 'profile-update', component: ProfileUpdateComponent, canActivate: [authGuard]},
  {path: 'products/:id', component: ProductDetailComponent},
  {path: 'order', component: OrderComponent, canActivate: [authGuard]},
  {path: 'order-history', component: OrderHistoryComponent, canActivate: [authGuard] },
  {path: 'paymentsuccessful', component: PaymentSuccessfulComponent, canActivate: [authGuard]},
  {path: 'orders', component: OrderComponent, canActivate: [authGuard]},
  {path: 'gift-cards', component: GiftCardsComponent},
  {path: 'gift-cards/:id', component: GiftCardDetailComponent},
  {path: 'admin-gift-cards', component: AdminGiftCardComponent},
  {path: 'admin-login', component: AdminLoginComponent}

];






