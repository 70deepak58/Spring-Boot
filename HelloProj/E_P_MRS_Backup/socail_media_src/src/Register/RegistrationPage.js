import { useState } from 'react';
import './RegistrationPage.css';
import logo from './image001.png';
import { useHistory } from 'react-router-dom';



function RegistrationPage() {
 
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [id, setId] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [error, setError] = useState('');
 
  
  const isValidate = (email) => {
    return email.toLowerCase().endsWith("@eversana.com");
  };
  const handleNameChange = (event) => {
    setName(event.target.value);
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



  const validate = () => {
    if (name === "") {
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
        body: JSON.stringify({  name, email, id, mobileNumber })
      });
      if (response.ok) {
        // Perform OTP verification and set isAuthenticated to true if successful
       

        // Redirect to the protected page
       
      history.push('/');
    } else {
      console.log(response.status);
    }}
    catch (error) {
      console.log(error);
    }
  }
  };

  return (
    <div className="registration-page">
      <div className='container1'>
        <div className='left'>
          <img  src={logo} alt="Logo" />
        </div>
        <div className='right'>
          <h1>Create an Account</h1>
          {error && <div className="error">{error}</div>}
          <form onSubmit={handleSubmit}>
            <label>
              Name:
              <input type="text" placeholder='Navin Kumar' value={name}  onChange={handleNameChange} />
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
          
            <button onClick={handleSubmit}>Register</button>
            
          </form>
        </div>
      </div> 
    </div>
  );
}

export default RegistrationPage;
