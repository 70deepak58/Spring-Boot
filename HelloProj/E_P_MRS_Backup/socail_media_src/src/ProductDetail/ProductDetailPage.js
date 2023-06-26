import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useHistory } from 'react-router-dom';
import './ProductDetailPage.css';



function ProductDetailPage() {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  
  const history = useHistory();

  useEffect(() => {
    fetchProduct();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const fetchProduct = async () => {
    try {
      const response = await axios.get(`http://localhost:8082/User/getProduct/${productId}`);
      setProduct(response.data);
      setLoading(false);
    } catch (error) {
      console.error(error);
    }
  };

  const handleEdit = () => {
    const empId = localStorage.getItem('empId');
    if (product && product.empId === empId) {
      history.push(`/edit/${productId}`);
    } else {
      console.log("You can only edit your own product.");
    }
  };
  //from here use logic of delete product by admin or product belongs to user can delete only
  //complete logic for it and apply


  //Now modifying this function plz remember
  const handleDelete =async (productId) => {
    console.log(productId);
    // const empId = localStorage.getItem('empId');
    // if (product && product.empId === empId) {
    //   history.push(`/deletePost/{productId}`);
    // } else {
    //   console.log("You can only delete your own product.");
    // }
    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');
    myHeaders.append("Authorization", "Bearer " + localStorage.getItem("token"));
    console.log("Bearer " + localStorage.getItem("token"))
    //console.log(myHeaders)
    // console.log("google")
    // console.log(`http://localhost:8081/pro/deletePostd/${productId}`);
    // const response = await fetch(`http://localhost:8081/pro/deleteAuth/${productId}/${localStorage.getItem('empId')}/${localStorage.getItem("token")}`, {  //user role start
    // credentials: 'include',
    // method: 'PUT',
    // headers: myHeaders
    // });
    const response = await fetch(`http://localhost:8081/pro/deleteAuth/${productId}/${localStorage.getItem('empId')}/${localStorage.getItem("token")}`, {  //user role start
    method: 'PUT'
    });
    if(response.ok){
      history.push(`/products`);
    }
    console.log(response);
  };
  const handleViewComments = () => {
    console.log(product);
    console.log(productId);
    localStorage.setItem('productId', productId);
    history.push(`/comments/${productId}`);
  };
  

  return (
    <div>
      <div>
      <nav className="navbar">
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
    </nav>
      </div>
      <div className='product-container'>
        {loading ? (
          <p>Loading...</p>
        ) : product ? (
          <div className="product-detail-page">
              <div className='containerr'>
                <div className='leftt'>
                  <img className="product-imagee" src={product.image} alt={product.productName} />
                </div>
                <div className='rightt'>
                  <h1 className="product-name">{product.productName}</h1>
                  <p className="product-info">Owner: {product.ownerName}</p>
                  <p className="product-info">Product ID: {product.productId}</p>
                  <p className="product-info">Mobile Number: {product.mobileNumber}</p>
                  <p className="product-description">{product.description}</p>
                  <p className="product-pricee">Price: {product.price}</p>
                </div>
            </div>
           
            
            {product.video && (
              <div className="product-video">
                <h3>Video</h3>
                <video src={product.video} controls />
              </div>
            )}
            <button onClick={handleEdit}>Edit</button>
            <button className='deletebutton' onClick={() => handleDelete(product.productId)}>Delete</button>
            <button onClick={handleViewComments}>Comments</button>
          </div>
        ) : (
          <p>No product found.</p>
        )}
      </div>
    </div>
  );
}

export default ProductDetailPage;