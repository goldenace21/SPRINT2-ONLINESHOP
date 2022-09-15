import {Product} from "./Product";
import {SignUpForm} from "./SignUpForm";

export interface Cart {
  id:number,
  quantity: number,
  product: Product,
  user: SignUpForm
}
