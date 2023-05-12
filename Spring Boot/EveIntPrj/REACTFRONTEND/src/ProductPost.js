import React, { useState } from 'react';
import axios from 'axios';
import './ProductPoststyle.css'
function ProductPost() {
  const [formData, setFormData] = useState({
    employeeId: '',
    ownerName: '',
    title: '',
    image: '',
    video: '',
    price: '',
    mobileNumber: '',
    description: '',
    buySellOption: '',
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  // const handleSubmit = (event) => {
  //   console.log("ggg")
  //   event.preventDefault();
  //   // make API call to post product data
  //   axios.post('/api/product', formData)
  //     .then(response => {
  //       console.log(response);
  //     })
  //     .catch(error => {
  //       console.log(error);
  //     });
  //   setFormData({
  //     employeeId: '',
  //     ownerName: '',
  //     title: '',
  //     image: '',
  //     video: '',
  //     price: '',
  //     mobileNumber: '',
  //     description: '',
  //     buySellOption: '',
  //   });
  // };

  const handleSubmit = async (event) => {

       setFormData({
      employeeId: '',
      ownerName: '',
      title: '',
      image: '',
      video: '',
      price: '',
      mobileNumber: '',
      description: '',
      buySellOption: '',
    });
    console.log("i me my mine")
    event.preventDefault();
    //image
    var input=document.getElementById("img");
    var data = new FormData()
    for(var i=0;i<input.files.length;i++){
		data.append('image', input.files[i])
	}

  //video
  var input=document.getElementById("vdo");
 // var data2 = new FormData()
  for(var i=0;i<input.files.length;i++){
  data.append('video', input.files[i])
}

//data
//var data3 = new FormData()
data.append('data', JSON.stringify({
  "emp_id":formData.employeeId,
  "approve":false,
  "owner_name":formData.ownerName,
  "product_name":formData.title,
  "price":formData.price,
  "mobile_no":formData.mobileNumber,
  "discription":formData.description,
  "adv_type":formData.buySellOption
  }))

//console.log(data)
    // make API call to post product data
    console.log("my",formData.buySellOption)
    const response = await fetch('http://localhost:8083/User/upload', {
      method: 'POST',
      body: data
    })
    console.log(response)

  };






  return (
    <div>
      <nav className="navbar">
        <div className="navbar-brand">EVERSANA</div>
        <ul className="navbar-nav-right">
          <li className="nav-item">
            <a href="http://localhost:3000/productapprovepage" className="nav-link">Admin Dashboard</a>
          </li>
          <li className="navbar-nav-right">
            <a href="/" className="nav-link">Product Page</a>
          </li>
          <li className="navbar-nav-right">
            <a href="/productpost" className="nav-link">Product Post Page</a>
          </li>
          <li className="navbar-nav-right">
            <a href="/" className="nav-link" onClick={() => localStorage.removeItem('token')}>Logout</a>
          </li>
        </ul>
      </nav>
      <div className="form-container">
      <label className='heading'>Product Post Form</label>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="employeeId">Employee ID</label>
            <input type="text" name="employeeId" placeholder='50000218' value={formData.employeeId} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="ownerName">Owner Name</label>
            <input type="text" name="ownerName" placeholder='Navin' value={formData.ownerName} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="title">Title</label>
            <input type="text" name="title" placeholder='Car,Home,Bike,furniture' value={formData.title} onChange={handleInputChange} />
          </div>
        
          <div className="form-group">
            <label htmlFor="price">Price</label>
            <input type="text" name="price" placeholder='$100' value={formData.price} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="mobileNumber">Mobile Number</label>
            <input type="tel" name="mobileNumber" placeholder='+91 9940309591'  value={formData.mobileNumber} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="description">Description</label>
            <textarea name="description" value={formData.description} onChange={handleInputChange} maxLength={2000} />
          </div>
          <div className="form-group">
            <label htmlFor="image">Upload Image </label>
            <input type="file" id="img" name="image" multiple value={formData.image} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="video">Upload Video </label>
            <input type="file" id="vdo" name="video" multiple value={formData.video} onChange={handleInputChange} />
          </div>
   
   
    <div className="form-group">
      <label htmlFor="option">Select Option:</label>
      <select
        name="option"
        id="option"
        className="form-control"
        value={formData.option}
        onChange={handleInputChange}
      >
        <option value="buy">Rent</option>
        <option value="sell">Sell</option>
      </select>
    </div>
    <button type="submit" className="btn btn-primary" >
      Submit
    </button>
  </form>
</div>
</div>
);
};

export default ProductPost;