import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import './ProductListingPage.css';

function ProductListingPage() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchProducts();
  }, []);
  
  const fetchProducts = async () => {
    try {
      const response = await axios.get('http://localhost:8082/User/getonlyTrue');
      setProducts(response.data);
      setLoading(false);
    } catch (error) {
      console.error(error);
    }
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
      <div className='nine'>
        <h1 >
        PRODUCTS</h1>
        </div>
      <div>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <div className='fluid-container'>
            <div className='row row-cols-1 row-cols-sm-2 row-cols-md-4'>
              {products.map((product) => (
                <div className='col m-3 product-cards' key={product.productId}>
                  <h2>
                    <Link to={`/productdetail/${product.productId}`}>{product.productName}</Link>
                  </h2>
                  <p className='product-emp'>EmpId: {product.empId}</p>
                  {product.image && (
                    <img className='product-image' src={product.image} alt={product.productName} />
                  )}
                </div>
              ))}
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default ProductListingPage;
