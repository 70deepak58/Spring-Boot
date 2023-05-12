package com.product.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.util.Pair;
@Document(collection="PRODUCT")
public class Product {

	    @Id
	    private ObjectId id;
	    private String emp_id;
	    private  boolean approve=false;
	    private List<Binary> image;
		private List<Binary> video;
		private  String owner_name;
		private String product_name;
		private int price;
		private String mobile_no;
		private String discription;
		private  String adv_type;
		private  List<String> comment=new ArrayList<String>();
		public Product() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Product(ObjectId id, String emp_id, boolean approve, List<Binary> image, List<Binary> video,
				String owner_name, String product_name, int price, String mobile_no, String discription,
				String adv_type, List<String> comment) {
			super();
			this.id=id;
			this.emp_id = emp_id;
			this.approve = approve;
			this.image = image;
			this.video = video;
			this.owner_name = owner_name;
			this.product_name = product_name;
			this.price = price;
			this.mobile_no = mobile_no;
			this.discription = discription;
			this.adv_type = adv_type;
			this.comment = comment;
		}
		
		
		
		public ObjectId getId() {
			return id;
		}
		public void setId(ObjectId id) {
			this.id = id;
		}
		public String getEmp_id() {
			return emp_id;
		}
		public void setEmp_id(String emp_id) {
			this.emp_id = emp_id;
		}
		public boolean isApprove() {
			return approve;
		}
		public void setApprove(boolean approve) {
			this.approve = approve;
		}
		public List<Binary> getImage() {
			return image;
		}
		public void setImage(List<Binary> image) {
			this.image = image;
		}
		public List<Binary> getVideo() {
			return video;
		}
		public void setVideo(List<Binary> video) {
			this.video = video;
		}
		public String getOwner_name() {
			return owner_name;
		}
		public void setOwner_name(String owner_name) {
			this.owner_name = owner_name;
		}
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getMobile_no() {
			return mobile_no;
		}
		public void setMobile_no(String mobile_no) {
			this.mobile_no = mobile_no;
		}
		public String getDiscription() {
			return discription;
		}
		public void setDiscription(String discription) {
			this.discription = discription;
		}
		public String isAdv_type() {
			return adv_type;
		}
		public void setAdv_type(String adv_type) {
			this.adv_type = adv_type;
		}
		public List<String> getComment() {
			return comment;
		}
		public void setComment(List<String> comment) {
			this.comment = comment;
		}
	
		
	

}
