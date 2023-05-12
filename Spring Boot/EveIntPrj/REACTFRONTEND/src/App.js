import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserProfilePage from './UserProfilePage';
import RegistrationPage from './RegistrationPage'
import ErrorPage from './ErrorPage';
import ProductPostPage from './ProductPost';
import ProductApprovepage from './ProductApprovepage';

function App() {
  return (
    <Router>
      <Switch>
      <Route exact path={"/"} component={UserProfilePage} />
      <Route exact path={"/register"} component={RegistrationPage} /> 
      <Route exact path={"/productpost"} component={ProductPostPage} />
      <Route exact path={"/productapprovepage"} component={ProductApprovepage} />
      <Route component={ErrorPage} />
      

      </Switch>
    </Router>
  );
}


export default App;
