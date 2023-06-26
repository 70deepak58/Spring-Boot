import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import LoginPage from './Login/LoginPage';
import RegistrationPage from './Register/RegistrationPage';
import ErrorPage from './ErrorPage';
import ProductPostPage from './ProductPost/ProductPost';
import ProductListingPage from './Product/ProductListingPage';
import ProductDetailPage from './ProductDetail/ProductDetailPage';
import ProductApprovalPage from './ProductApproval/ProductApprovalPage';
import EditProductPage from './EditProduct/EditProductPage';
import MakeAdminPage from './MakeAdmin';
import Comments from './Comment';
import './Navbarstyle.css'
const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = React.useState(localStorage.getItem('isAuthenticated') === 'true');
  const [empId, setEmpId] = React.useState(localStorage.getItem('empId') || '');

  const login = (empId) => {
    setIsAuthenticated(true);
    localStorage.setItem('isAuthenticated', true);
    localStorage.setItem('empId', empId);
    setEmpId(empId);
  };

  const logout = () => {
    setIsAuthenticated(false);
    localStorage.removeItem('isAuthenticated',false);
    localStorage.removeItem('empId');
    setEmpId('');
  };
 

  return { isAuthenticated, empId, login, logout };
};


// Higher-order component for protected routes
const ProtectedRoute = ({ component: Component, auth, ...rest }) => {
  const { isAuthenticated } = auth;

  return (
    <Route
      {...rest}
      render={(props) =>
        isAuthenticated ? <Component auth={auth} {...props} /> : <Redirect to="/" />
      }
    />
  );
};

function App() {
  const auth = useAuth();
  if (auth.isAuthenticated) {
    console.log('User is authenticated');
  } else {
    console.log('User is not authenticated');
  }
  return (
    <Router>
     {/* <Navbar auth={auth} /> */}
      <Switch>
        <Route exact path="/">
          <LoginPage auth={auth} />
        </Route>
        <Route exact path="/register" component={RegistrationPage} />
       
        {/* Protected routes */}
        <ProtectedRoute
          exact
          path="/productpost"
          component={ProductPostPage}
          auth={auth}
        />
      
        <ProtectedRoute
          exact
          path="/products"
          component={ProductListingPage}
          auth={auth}
        />
        <ProtectedRoute
          exact
          path="/productdetail/:productId"
          component={ProductDetailPage}
          auth={auth}
        />
        <ProtectedRoute
          exact
          path="/admin"
          component={ProductApprovalPage}
          auth={auth}
        />
        <ProtectedRoute
          exact
          path="/edit/:productId"
          component={EditProductPage}
          auth={auth}
        />
        <ProtectedRoute
          exact
          path="/makeadmin"
          component={MakeAdminPage}
          auth={auth}
        />
          
        <ProtectedRoute
          exact
          path="/comments/:productId"
          component={Comments}
          auth={auth}
        />

        {/* Other routes */}
        <Route component={ErrorPage} />
      </Switch>
    </Router>
  );
}

export default App;
