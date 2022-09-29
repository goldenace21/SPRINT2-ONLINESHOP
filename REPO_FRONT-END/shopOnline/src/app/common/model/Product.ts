import {Category} from "./Category";

export interface Product {
  id:number;
  name: string;
  price: number;
  img: string;
  detail: string;
  origin: string;
  dateCreate: string;
  productCategory: Category
  deleteStatus: boolean
}
