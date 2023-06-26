import React, { useState } from 'react';
import axios from 'axios';


function MakeAdminPage() {
  const [id, setempId] = useState('');
  const [userDetails, setUserDetails] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleInputChange = (event) => {
    setempId(event.target.value);
  };


  const handleSearch = async () => {
    try {
      let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: `http://localhost:8081/pro/search/${id}`,
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem("token")
        }
      };
      axios.request(config)
        .then((response) => {
          setUserDetails(response.data);
          //console.log(response.data);
          setSuccessMessage('');
          setErrorMessage('');
          console.log(JSON.stringify(response.data));
        })
        .catch((error) => {
          console.log(error);
        });
    } catch (error) {
      setUserDetails(null);
      setErrorMessage('Error retrieving user details.');
      console.error(error);
    }
  };

  const handleMakeAdmin = async () => {
    try {


      const myHeaders = new Headers();
      myHeaders.append('Content-Type', 'application/json');
      myHeaders.append("Authorization", "Bearer " + localStorage.getItem("token"));
      console.log("Bearer " + localStorage.getItem("token"))
      console.log(myHeaders)
      const response = await fetch(`http://localhost:8081/pro/makeAdmin/${id}`, {  //user role start
        credentials: 'include',
        method: 'PUT',
        headers: myHeaders
      });
      if (response.ok) {
        setSuccessMessage('User has been made an admin.');
        setempId('');
        setUserDetails(null);
      }
      else {
        setErrorMessage('Error making user admin.');
      }
    } catch (error) {
      console.error(error);
    }
  };
  const handlerevertAdmin = async () => {
    try {


      const myHeaders = new Headers();
      myHeaders.append('Content-Type', 'application/json');
      myHeaders.append("Authorization", "Bearer " + localStorage.getItem("token"));
      console.log("Bearer " + localStorage.getItem("token"))
      console.log(myHeaders)
      const response = await fetch(`http://localhost:8081/pro/revertAdmin/${id}`, {  //user role start
        credentials: 'include',
        method: 'PUT',
        headers: myHeaders
      });
      if (response.ok) {
        setSuccessMessage('Admin has been made a user.');
        setempId('');
        setUserDetails(null);
      }
      else {
        setErrorMessage('Error making admin user.');
      }
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
      <h1>Make Admin Page</h1>
      <div>
        <input type="text" value={id} onChange={handleInputChange} placeholder="Enter EmpId" />
        <button onClick={handleSearch}>Search</button>
      </div>
      {userDetails && (
        <div>
          <h2>User Details</h2>
          <p>EmpId: {userDetails.id}</p>
          <p>Name: {userDetails.name}</p>
          <p>Email: {userDetails.email}</p>
          <p>Role: {userDetails.roles}</p>
          <button onClick={handleMakeAdmin}>Make Admin</button>
          <button onClick={handlerevertAdmin}>Revoke Admin Rights</button>
        </div>
      )}
      {successMessage && <p>{successMessage}</p>}
      {errorMessage && <p>{errorMessage}</p>}
    </div>
  );
}

export default MakeAdminPage;
