import { useState } from 'react';
import './protection'
import logo from './image001.png';
import { useHistory } from 'react-router-dom'
import './UserProfilePage.css';


function LoginPage() {
  const [id, setId] = useState('');
  const [pwd, setPwd] = useState('');
  const [error, setError] = useState('');
  const [flag, setFlag] = useState(0);

  const handleIdChange = (event) => {
    setId(event.target.value);
  };

  const handlePwdChange = (event) => {
    setPwd(event.target.value);
  };

  const validate = () => {
    if (id === "") {
      setError("User Name can't be empty");
      return false;
    }
    return true;
  };

  const handleSetFlagChange = () => {
    setFlag(1);
  };

  //generate OTP
  const handleGenerateOTPClick = async (event) => {
    console.log("OTP sending");
    handleSetFlagChange();
    event.preventDefault();
    const response = await fetch('http://localhost:8081/pro/otp/generate', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id, pwd })
    });
    console.log("OTP sent");

  }

  const handleLoginClick = async (event) => {
    let URL_HIT = "";
    event.preventDefault();
    if (validate()) {
      try {
        //logic to change type of login
        if (flag == 0) {
          //logic of password
          URL_HIT = 'http://localhost:8081/pro/authenticate';
          console.log("loggedin by using password");
        }
        else {
          //logic of OTP
          URL_HIT = 'http://localhost:8081/pro/otp/authenticate';
          handleSetFlagChange();
          console.log("loggedin by using OTP");
        }
       //Hit URL accordingly
        const response = await fetch(URL_HIT, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json',
          },
          body: JSON.stringify({ id, pwd })
        });
        console.log(response);
        if (response.ok) {
          console.log(response);
          const data = await response.json();
          localStorage.setItem('jwt', data.token);
          console.log(data.token); // handle login success or failure here

          //history.push('/productpost');


          // yaha se code hatao
          console.log("checking auth for user role and admin role");
          const myHeaders = new Headers();
          myHeaders.append('Content-Type','application/json');
          myHeaders.append("Authorization","Bearer "+data.token);
          console.log("Bearer "+data.token)
          console.log(myHeaders)

          const userRole= await fetch('http://localhost:8081/pro/second', {       //user role start
            credentials: 'include',
            method: 'GET',
            headers: myHeaders
          });
          if(userRole.ok){
            const userRoleData = await userRole.json();
            console.log(userRoleData);
            history.push('/productpost');
          }
          else{
            console.log("failed auth user");
          }
          const adminRole= await fetch('http://localhost:8081/pro/second2', {      //admin role start
            credentials: 'include',
            method: 'GET',
            headers: myHeaders
          });
          if(adminRole.ok){
            const adminRoleData = await adminRole.json();
            console.log(adminRoleData);
            history.push('/productapprovepage');
          }
          else{
            console.log("failed auth admin");
          }
          
          


          //yaha tak code hatana hai
        } else {
          console.log(response.status);
          // handle login failure here
        }


      } catch (error) {
        console.log(error);
      }
    }
  };


  const history = useHistory();
  function handleRegisterClick(event) {
    event.preventDefault();
    history.push('/register');

  }


  return (
    <div className="login-page">
      <img src={logo} alt="Logo" />
      {error && <div className="error">{error}</div>}
      <h1>Login</h1>

      <form>
        <label>
          Employee ID:
          <input required type="text" value={id} placeholder='50000218' onChange={handleIdChange} />
        </label>
        <label>
          Password:
          <input required type="password" placeholder='********' value={pwd} onChange={handlePwdChange} id="lgin" />
        </label>
        <button onClick={handleLoginClick}>Login</button>
        <p>Or, Use OTP?</p>
        <button onClick={handleGenerateOTPClick} id="otpg">Generate OTP</button>
        <p>Or, Don't have an account?</p>
        <button onClick={handleRegisterClick}>Register</button>
      </form>


    </div>
  );
}

export default LoginPage;
