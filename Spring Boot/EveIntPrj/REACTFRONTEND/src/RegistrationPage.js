import { useState } from 'react';
import './RegistrationPage.css';
import logo from './image001.png';
import { useHistory } from 'react-router-dom';



function RegistrationPage() {
 
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [id, setId] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [password, setPassword] = useState('');
  const[confirmPassword, setConfirmPassword] =useState('')
  const [error, setError] = useState('');
 
  
  const isValidate = (email) => {
    return email.toLowerCase().endsWith("@eversana.com");
  };
  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };
  
  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handleIdChange = (event) => {
    setId(event.target.value);
  };

  const handleMobileNumberChange = (event) => {
    setMobileNumber(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };
  const handleConfirmPasswordChange = (event) => {
    setConfirmPassword(event.target.value);
  };


  const validate = () => {
    if (username === "") {
      setError("User Name can't be empty");
      return false;
    }
    if (mobileNumber === "") {
      setError("Mobile Number can't be empty");
      return false;
     
    }
    if (!isValidate(email)) {
      setError("Please provide correct email");
      return false;
    }
    if (password.length < 8) {
      setError("Weak Password");
      return false;
    }
    if (password !== confirmPassword) {
      setError("Password must match");
      return false;
    }
    
    return true;
  };
  const history = useHistory();
  const handleSubmit = async (event) => {
    event.preventDefault();
    if (validate()) {
   
    try {
      const response = await fetch('http://localhost:8081/pro/addUser', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({  username, email, id, mobileNumber, password })
      });
      const data = await response.json();
      console.log(data); 
      history.push('/');
    } catch (error) {
      console.log(error);
    }
  }
  };

  return (
    <div className="registration-page">
     <img  src={logo} alt="Logo" />
      <h1>Create an Account</h1>
      {error && <div className="error">{error}</div>}
      <form onSubmit={handleSubmit}>
      
        <label>
          Name:
          <input type="text" placeholder='Navin Kumar' value={username}  onChange={handleUsernameChange} />
        </label>
        <label>
          Email:
          <input type="email"  placeholder='navin.kumar@eversana.com' value={email}  onChange={handleEmailChange} />
        </label>
        <label>
          Employee ID:
          <input type="text" placeholder='50000218' value={id}   onChange={handleIdChange} />
        </label>
        <label>
          Mobile Number:
          <input type="tele" placeholder='9801854388' value={mobileNumber}  onChange={handleMobileNumberChange} />
        </label>
        <label>
          Password:
          <input type="password" placeholder='********' value={password}   onChange={handlePasswordChange} />
        </label>
        <label>
          Confirm Password:
          <input type="password" placeholder='********' value={confirmPassword}  onChange={handleConfirmPasswordChange} />
        </label>
        <button onClick={handleSubmit}>Register</button>
        
      </form>
    </div>
  );
}

export default RegistrationPage;
