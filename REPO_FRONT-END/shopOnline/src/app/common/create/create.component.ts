import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ProductService} from "../service/product.service";
import {Category} from "../model/Category";
import {FormControl, FormGroup} from "@angular/forms";
import {formatDate} from "@angular/common";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute} from "@angular/router";
import {Product} from "../model/Product";
import {AngularFireStorage} from "@angular/fire/storage";
import {finalize} from "rxjs/operators";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  url: any;
  msg = '';
  selectedFile: File = null;


  id: string;
  name: string;
  detail: string;
  price: string;
  imgPath: string;
  product: Product;
  productCategory: Category;
  categorys: Category[]
  productForm = new FormGroup({
    id: new FormControl(""),
    name: new FormControl(""),
    img: new FormControl(""),
    detail: new FormControl(""),
    price: new FormControl(""),
    origin: new FormControl("Apple"),
    dateCreate: new FormControl(this.getCurrentDateTime()),
    productCategory: new FormControl(),
    deleteStatus: new FormControl(false)
  });

  constructor(private title: Title,
              private productService: ProductService,
              private toast: ToastrService,
              private activatedRoute: ActivatedRoute,
              private storage: AngularFireStorage) {
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'yyyy-MM-dd hh:mm:ss', 'en-US');
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0];
    this.productForm.patchValue({img: this.selectedFile.name});
    const reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);

    // tslint:disable-next-line:variable-name
    reader.onload = (_event) => {
      this.msg = '';
      this.url = reader.result;
    };
  }


  ngOnInit() {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");

    if (this.id == "0") {
      this.title.setTitle("Apple - Create")
    } else {
      this.title.setTitle("Apple - Update")
    }
    this.productService.getListCategory().subscribe(
      value => this.categorys = value
    )
    console.log(this.id)
    if (this.id == "0") {
      this.id = ""
      console.log("==0")
    } else {
      this.productService.detail(this.id).subscribe(
        value => {this.product = value;
          console.log("!=0")
          this.id = String(this.product.id)
          this.name = this.product.name
          this.imgPath = this.product.img
          this.detail = this.product.detail
          this.price = String(this.product.price)
          this.productCategory = this.product.productCategory
        });
    }
  }

  create() {
    const nameImg = this.getCurrentDateTime() + this.selectedFile.name;
    const filePath = `apple/${nameImg}`;
    const fileRef = this.storage.ref(filePath);
    this.storage.upload(`apple/${nameImg}`, this.selectedFile).snapshotChanges().pipe(
      finalize(() => {
        fileRef.getDownloadURL().subscribe((url) => {
          this.productForm.patchValue({img: url});
          // console.log(url);
          // console.log(this.formNews.value);
          this.productService.save(this.productForm.value).subscribe(value => this.toast.success("create successfully"))
        });
      })
    ).subscribe();
  }
}
