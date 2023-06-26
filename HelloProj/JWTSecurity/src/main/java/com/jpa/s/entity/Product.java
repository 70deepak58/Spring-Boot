package com.jpa.s.entity;

//import java.util.ArrayList;
//import java.util.List;
//
//import org.bson.types.Binary;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

//it was initial config now i want to change
public class Product {

	
	private String name;
	private int  qty;
	private int  price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(String name, int qty, int price) {
		super();
		this.name = name;
		this.qty = qty;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", qty=" + qty + ", price=" + price + "]";
	}
	
	
}

////Try adding here
//
////@Document(collection = "products")
//public class Product {
//
//    @Id
//    //@Field("_id")
//    private String id;
//    private String empId;
//    private String ownerName;
//    private String productName;
//    private List<Binary> image;
//    private List<Binary> video;
//    private int price;
//    private String mobileNumber;
//    private String description;
//    private String advType;
//    private boolean approve;
//    private List<String> comment;
//
//    public Product() {
//        // Empty constructor required by MongoDB
//    }
//    public Product(String empId) {
//        this.empId = empId;
//    }
//
//    public Product(String empId, String ownerName, String productName, List<Binary> image, List<Binary> video,
//            int price, String mobileNumber, String description, String advType) {
//        this.empId = empId;
//        this.ownerName = ownerName;
//        this.productName = productName;
//        this.image = image;
//        this.video = video;
//        this.price = price;
//        this.mobileNumber = mobileNumber;
//        this.description = description;
//        this.advType = advType;
//        this.approve = false; // Default value for approve
//        this.comment = new ArrayList<>(); // Initialize comment list
//    }
//
//    // Getters and setters
//    public String getProductId() {
//        return id;
//    }
//
//    public void setProductId(String id) {
//        this.id = id;
//    }
//
//    public String getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(String empId) {
//        this.empId = empId;
//    }
//
//    public String getOwnerName() {
//        return ownerName;
//    }
//
//    public void setOwnerName(String ownerName) {
//        this.ownerName = ownerName;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public List<Binary> getImage() {
//        return image;
//    }
//
//    public void setImage(List<Binary> image) {
//        this.image = image;
//    }
//
//    public List<Binary> getVideo() {
//        return video;
//    }
//
//    public void setVideo(List<Binary> video) {
//        this.video = video;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public void setMobileNumber(String mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getAdvType() {
//        return advType;
//    }
//
//    public void setAdvType(String advType) {
//        this.advType = advType;
//    }
//
//    public boolean isApprove() {
//        return approve;
//    }
//
//    public void setApprove(boolean approve) {
//        this.approve = approve;
//    }
//
//    public List<String> getComment() {
//        return comment;
//    }
//
//    public void setComment(List<String> comment) {
//        this.comment = comment;
//    }
//}
