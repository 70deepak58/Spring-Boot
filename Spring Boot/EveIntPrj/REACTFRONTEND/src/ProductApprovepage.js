import React, { useState, useEffect } from "react";
import './ProductApprovepage.css'
import axios from "axios";
import './ProductApprovepage.css'

const AdminPage = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:5000/api/products").then((response) => {
      setProducts(response.data);
    });
  }, []);

  const approveProduct = (id) => {
    axios.put(`http://localhost:5000/api/products/${id}`, {
      isApproved: true,
    });
    setProducts(products.filter((product) => product._id !== id));
  };

  const deleteProduct = (id) => {
    axios.delete(`http://localhost:5000/api/products/${id}`);
    setProducts(products.filter((product) => product._id !== id));
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

      <div className="products-container">
        {products.map((product) => (
          <div className="product" key={product._id}>
            <img src={product.images[0]} alt={product.title} />
            <h3>{product.title}</h3>
            <div className="product-actions">
              <button className="approve-button" onClick={() => approveProduct(product._id)}>
                Approve
              </button>
              <button className="delete-button" onClick={() => deleteProduct(product._id)}>
                Delete
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AdminPage;
