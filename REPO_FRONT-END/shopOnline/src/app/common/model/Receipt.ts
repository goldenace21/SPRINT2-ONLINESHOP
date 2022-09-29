import {LoginForm} from "./LoginForm";
import {Cart} from "./Cart";

export interface Receipt {
  id?: number;
  date?: string;
  totalPrice?: string;
  userReceipt?: LoginForm
  cart?: Cart[];
}
