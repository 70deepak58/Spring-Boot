import React, { useState } from 'react';
import axios from 'axios';
import './ProductPoststyle.css';

function ProductPost() {
  const [formData, setFormData] = useState({
    empId: '',
    ownerName: '',
    productName: '',
    image: null,
    video: null,
    price: '',
    mobileNumber: '',
    description: '',
    advType: 'buy', // Set an initial value for advType
  });

  const handleInputChange = (event) => {
    const { name, value, type } = event.target;
  
    if (type === 'file') {
      const file = event.target.files[0];
      setFormData((prevFormData) => ({
        ...prevFormData,
        [name]: [file],
      }));
    } else {
      setFormData((prevFormData) => ({
        ...prevFormData,
        [name]: value,
      }));
    }
  };
  

  const handleSubmit = (event) => {
    event.preventDefault();

    const postData = new FormData();
    postData.append('empId', formData.empId);
    postData.append('ownerName', formData.ownerName);
    postData.append('productName', formData.productName);
    postData.append('price', formData.price);
    postData.append('mobileNumber', formData.mobileNumber);
    postData.append('description', formData.description);
    postData.append('advType', false);

    // Append image files
    if (formData.image) {
      if (Array.isArray(formData.image)) {
        formData.image.forEach((file) => {
          postData.append('image', file);
        });
      } else {
        postData.append('image', formData.image);
      }
    }

    // Append video files
    if (formData.video) {
      if (Array.isArray(formData.video)) {
        formData.video.forEach((file) => {
          postData.append('video', file);
        });
      } else {
        postData.append('video', formData.video);
      }
    }

    // make API call to post product data
    axios
      .post('http://localhost:8082/User/upload', postData)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });

    // Reset form data
    setFormData({
      empId: '',
      ownerName: '',
      productName: '',
      image: null,
      video: null,
      price: '',
      mobileNumber: '',
      description: '',
      advType: '',
    });
  };

  return (
    <div>
     <div><nav className="navbar">
      <div className="navbar-brand">EVERSANA</div>
      <ul className="navbar-nav-right">
        <li className="nav-item">
          <a href="http://localhost:3000/admin" className="nav-link">
            Admin Dashboard
          </a>
        </li>
        <li className="navbar-nav-right">
          <a href="/products" className="nav-link">
            Product Page
          </a>
        </li>
        <li className="navbar-nav-right">
          <a href="/productpost" className="nav-link">
            Product Post Page
          </a>
        </li>
        <li className="navbar-nav-right">
          <a href="/" className="nav-link" onClick={() => localStorage.removeItem('isAuthenticated')}>
            Logout
          </a>
        </li>
      </ul>
    </nav></div>
      <div className="form-container">
        <h1 className='heading'>Product Post Form</h1>
       
        <form onSubmit={handleSubmit} encType="multipart/form-data">
          <div className="form-group">
            <label htmlFor="empId">Employee ID</label>
            <input type="text" name="empId" placeholder='50000218' value={formData.empId} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="ownerName">Owner Name</label>
            <input type="text" name="ownerName" placeholder='Navin' value={formData.ownerName} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="productName">Product Name</label>
            <input type="text" name="productName" placeholder='Car,Home,Bike,furniture' value={formData.productName} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="price">Price</label>
            <input type="text" name="price" placeholder='$100' value={formData.price} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="mobileNumber">Mobile Number</label>
            <input type="tel" name="mobileNumber" placeholder='+91 9940309591' value={formData.mobileNumber} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="image">Upload Image</label>
            <input type="file" name="image" onChange={handleInputChange} />
            {/* {formData.image && (
              <span>{formData.image.name}</span>
            )} */}
          </div>
          <div className="form-group">
            <label htmlFor="video">Upload Video</label>
            <input type="file" name="video" onChange={handleInputChange} />
            {/* {formData.video &&(
             <span>{formData.video.name}</span>
            )} */}
          </div>
          <div className="form-group">
            <label htmlFor="advType">Select Option:</label>
            <select
              name="advType"
              id="advType"
              className="form-control"
              value={formData.advType}
              onChange={handleInputChange}
            >
              <option value="buy">Rent</option>
              <option value="sell">Sell</option>
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="description">Description</label>
            <textarea name="description" value={formData.description} onChange={handleInputChange} maxLength={2000} />
          </div>
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </form>
      </div>
    </div>
);
};

export default ProductPost;
