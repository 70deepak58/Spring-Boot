import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useHistory } from 'react-router-dom';
import './EditProductPage.css';
function EditProductPage() {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [productName, setProductName] = useState('');
  const [ownerName, setOwnerName] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const [video, setVideo] = useState(null);
  const [image, setImage] = useState(null);

  useEffect(() => {
    fetchProduct();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const fetchProduct = async () => {
    try {
      const response = await axios.get(`http://localhost:8082/User/getProduct/${productId}`);
      setProduct(response.data);
      setLoading(false);
      if (response.data) {
        setProductName(response.data.productName);
        setOwnerName(response.data.ownerName);
        setMobileNumber(response.data.mobileNumber);
        setDescription(response.data.description);
        setPrice(response.data.price);
      }
    } catch (error) {
      console.error(error);
    }
  };
  const history = useHistory();

  const handleEditProduct = async (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append('empId', product.empId); // Add the empId to the form data
    formData.append('productName', productName);
    formData.append('ownerName', ownerName);
    formData.append('mobileNumber', mobileNumber);
    formData.append('description', description);
    formData.append('price', price);
    formData.append('video', video);
    formData.append('image', image);

    try {


      // Make the PUT request with the updated product object
      await axios.put(`http://localhost:8082/User/updateProduct/${productId}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      // Redirect to the product detail page after successful update
      //  window.location.href = `/product/${productId}`;
      history.push(`/products`);
    } catch (error) {
      console.error(error);
    }
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
      <div className="edit-product-container">

        {loading ? (
          <p>Loading...</p>
        ) : product ? (
          <div className='edit-main-container'>
            <h1>Edit Product</h1>
            <div className="edit-form-field">
              <label htmlFor="productName">Product Name:</label>
              <input
                type="text"
                id="productName"
                value={productName}
                onChange={(e) => setProductName(e.target.value)}
              />
            </div>

            <div className="edit-form-field">
              <label htmlFor="ownerName">Owner Name:</label>
              <input
                type="text"
                id="ownerName"
                value={ownerName}
                onChange={(e) => setOwnerName(e.target.value)}
              />
            </div>

            <div className="edit-form-field">
              <label htmlFor="mobileNumber">Mobile Number:</label>
              <input
                type="text"
                id="mobileNumber"
                value={mobileNumber}
                onChange={(e) => setMobileNumber(e.target.value)}
              />
            </div>

            <div className="edit-form-field">
              <label htmlFor="description">Description:</label>
              <textarea
                id="description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
              ></textarea>
            </div>

            <div className="edit-form-field">
              <label htmlFor="price">Price:</label>
              <input type="number" id="price" value={price} onChange={(e) => setPrice(e.target.value)} />
            </div>

            <div className="edit-form-field">
              <label htmlFor="image">Image:</label>
              <input type="file" id="image" onChange={(e) => setImage(e.target.files[0])} />
            </div>

            <div className="edit-form-field">
              <label htmlFor="video">Video:</label>
              <input type="file" id="video" onChange={(e) => setVideo(e.target.files[0])} />
            </div>

            <button className="save-button" onClick={handleEditProduct}>
              Save
            </button>
          </div>
        ) : (
          <p>No product found.</p>
        )}
      </div>
    </div>
  );
}

export default EditProductPage;
