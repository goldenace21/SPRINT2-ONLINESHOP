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

  checkImgUpdate = false;

  imgFire: string = "";

  id: string;
  name: string;
  detail: string;
  price: string;
  product: Product;
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
    return formatDate(new Date(),'yyyy-MM-dd hh:mm:ss', 'en-US');
  }

  onFileSelected(event) {
    this.checkImgUpdate = true;
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
    } else {
      this.productService.detail(this.id).subscribe(
        value => {
          this.product = value;
          this.imgFire = this.product.img
          this.productForm.patchValue({id: this.product.id});
          this.productForm.patchValue({name: this.product.name});
          this.productForm.patchValue({img: this.product.img});
          this.productForm.patchValue({detail: this.product.detail});
          this.productForm.patchValue({price: this.product.price});
          this.productForm.patchValue({productCategory: this.product.productCategory});
        });
    }
  }

  create() {
    if (this.id != "0" && !this.checkImgUpdate) {
      this.productService.save(this.productForm.value).subscribe(value => {history.back();this.toast.success("update successfully")})
      return
    }
    const nameImg = this.getCurrentDateTime() + this.selectedFile.name;
    const filePath = `apple/${nameImg}`;
    const fileRef = this.storage.ref(filePath);
    this.storage.upload(`apple/${nameImg}`, this.selectedFile).snapshotChanges().pipe(
      finalize(() => {
        fileRef.getDownloadURL().subscribe((url) => {
          this.productForm.patchValue({img: url});
          this.productService.save(this.productForm.value).subscribe(value => {
            history.back();
            if (this.id != "0") {
              this.toast.success("update successfully")
            } else {
              this.toast.success("create successfully")
            }
            })
          }
        );
       }
      )
    ).subscribe();
  }

  compareFn(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }
}

