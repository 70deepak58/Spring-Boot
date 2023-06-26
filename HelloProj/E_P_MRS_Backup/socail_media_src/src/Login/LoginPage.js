import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import logo from './image001.png';
import './UserProfilePage.css';

function LoginPage({ auth }) {
  const [id, setId] = useState('');
  const [otp, setOtp] = useState('');
  const [error, setError] = useState('');
  const [isOTPGenerated, setIsOTPGenerated] = useState(false);
  const history = useHistory();

  const handleIdChange = (event) => {
    setId(event.target.value);
  };

  const handleOtpChange = (event) => {
    setOtp(event.target.value);
  };

  const validate = () => {
    if (id.trim() === '') {
      setError("Employee ID can't be empty");
      return false;
    }
    if (!isOTPGenerated) {
      setError('Please generate OTP');
      return false;
    }
    if (otp.trim() === '') {
      setError("OTP can't be empty");
      return false;
    }
    return true;
  };


  // const handleLoginClick = async (event) => {
  //   event.preventDefault();
  //   if (validate()) {
  //     try {
  //       const response = await fetch('http://localhost:8081/pro/otp/authenticate', {
  //         method: 'POST',
  //         headers: { 'Content-Type': 'application/json' },
  //         body: JSON.stringify({ id, otp }),
  //       });
  
  //       if (response.ok) {
  //          // Perform OTP verification and set isAuthenticated to true if successful
          
  //         auth.login();     // Call the login function from the auth object
         
  //         history.push('/products');
  //       } else {
  //         setError('Incorrect OTP. Please try again.');
  //       }
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   }
  // };
  const handleLoginClick = async (event) => {
    event.preventDefault();
    if (validate()) {
      try {
        const response = await fetch('http://localhost:8081/pro/otp/authenticate', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ id, otp }),
        });
  
        if (response.ok) {
          try {
            const responseData = await response.json();
            const empId = responseData.empId;
            const token =responseData.token;
            console.log(token);
            
            if (empId) {
              auth.login();
              localStorage.setItem('empId', empId);
              localStorage.setItem('token', token);
              history.push('/products');
            } else {
              setError('Invalid response data. Please try again.');
            }
          } catch (jsonError) {
            setError('Invalid response data. Please try again.');
          }
        } else {
          setError('Incorrect OTP. Please try again.');
        }
      } catch (error) {
        console.log(error);
      }
    }
  };
  
  
  

  const handleGenerateOTP = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch('http://localhost:8081/pro/otp/generate', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id }),
      });

      if (response.ok) {
        setIsOTPGenerated(true);
      } else {
        console.log('Response status:', response.status);
        // Handle the case when the API returns an error
      }
    } catch (error) {
      console.log('Error:', error);
      // Handle any network or server errors
    }
  };

  const handleRegisterClick = (event) => {
    event.preventDefault();
    history.push('/register');
  };

  return (
    <div className="login-page">
      <div className='container1'>
        <div className='left'>
          <img src={logo} alt="Logo" />
          {error && <div className="error">{error}</div>}
        </div>
        <div className='right'>
          <h1>Login</h1>
          <br></br>
          <form>
            <label>
              Employee ID:
              <input
                required
                type="text"
                value={id}
                placeholder="Enter your Employee ID"
                onChange={handleIdChange}
              />
            </label>
            {isOTPGenerated && (
              <label>
                OTP:
                <input
                  required
                  type="text"
                  value={otp}
                  placeholder="- - - - - -"
                  onChange={handleOtpChange}
                />
              </label>
            )}
            {isOTPGenerated ? (
              <button onClick={handleLoginClick}>Login</button>
            ) : (
              <button onClick={handleGenerateOTP}>Generate OTP</button>
            )}
            <p>Or, don't have an account?</p>
            <button onClick={handleRegisterClick}>Register</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
