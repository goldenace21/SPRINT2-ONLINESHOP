import {Product} from "./Product";
import {SignUpForm} from "./SignUpForm";

export interface Cart {
  productItem: any;
  id:number,
  quantity: number,
  product: Product,
  user: SignUpForm
}
