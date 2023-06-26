import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useHistory } from 'react-router-dom';
import './ProductApprovepage.css';

function AdminApprovalPage() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const history = useHistory();

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get('http://localhost:8082/User/getAllFalse');
      setProducts(response.data);
      setLoading(false);
    } catch (error) {
      console.error(error);
    }
  };

  const handleApprove = async (productId) => {
    try {
      //for security resason delete below comments and also delete its functionalty from backend as it may cause securty flaw

      //Navin method
      ///await axios.put(`http://localhost:8082/User/approveProduct/${productId}`);

      //dhfjhdjh hdf jdhfdjhfj
      //await axios.put(`http://localhost:8082/User/approveProduct/${productId}`);
      // Refresh the product list after approval

      const myHeaders = new Headers();
      myHeaders.append('Content-Type', 'application/json');
      myHeaders.append("Authorization", "Bearer " + localStorage.getItem("token"));
      console.log("Bearer " + localStorage.getItem("token"))
      console.log(myHeaders)
      const response = await fetch(`http://localhost:8081/pro/approveProductd/${productId}`, {  //user role start
        credentials: 'include',
        method: 'PUT',
        headers: myHeaders
      });


      fetchProducts();
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (productId) => {
    try {
      //for securtiy reson delete below comments and also delete its functionalty from backend as it may cause securty flaw


      //Navin method
      // await axios.delete(`http://localhost:8082/User/deletePost/${productId}`);
      // // Refresh the product list after deletion

      const myHeaders = new Headers();
      myHeaders.append('Content-Type', 'application/json');
      myHeaders.append("Authorization", "Bearer " + localStorage.getItem("token"));
      console.log("Bearer " + localStorage.getItem("token"))
      //console.log(myHeaders)
      // console.log("google")
      // console.log(`http://localhost:8081/pro/deletePostd/${productId}`);
      const response = await fetch(`http://localhost:8081/pro/deletePostd/${productId}`, {  //user role start

        credentials: 'include',
        method: 'PUT',
        headers: myHeaders
      });
      fetchProducts();
    } catch (error) {
      console.error(error);
    }
  };

  const handleMakeAdmin = () => {
    history.push('/makeadmin');
  };
  return (
    <div>
      <div> <nav className="navbar">
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
      <div className='twelve'><h1 >ADMIN DASHBOARD</h1></div>

      {loading ? (
        <p>Loading...</p>
      ) : (
        <>
          <button onClick={handleMakeAdmin}>Make Admin</button>
          <div className="producttt">
            {products.map((product) => (
              <div key={product.productId} className="productttt">
                <h2>
                  <Link to={`/productdetail/${product.productId}`}>{product.productName}</Link>
                </h2>
                <p>EmpId: {product.empId}</p>
                {product.image && <img src={product.image} alt={product.productName} />}
                <div className="buttons-container">
                  <button onClick={() => handleApprove(product.productId)}>Approve</button>
                  <button onClick={() => handleDelete(product.productId)}>Delete</button>
                </div>
              </div>
            ))}


          </div>

        </>
      )}
    </div>
  );
}

export default AdminApprovalPage;
