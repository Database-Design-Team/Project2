import React from "react";
import { useHistory, useLocation } from "react-router-dom";
import "./Dashboard.scss";

const Dashboard = props => {
  let history = useHistory();
  const location = useLocation();
  const handleSignOut = () => {
    history.push(`/`);
  };
  return (
    <div className="dashboard-container">
      <div className="header-container">
        <div className="home-container">Coog Music</div>
        <div className="search-container">
          <input type="text" placeholder="Search" />
          <i class="fa fa-search"></i>
        </div>
        <div className="btn-group">
          <div>
            <button className="btn btn-sign-out" onClick={handleSignOut}>
              Sign out
            </button>
          </div>
        </div>
      </div>
      <div className="dashboard-body-container">
        <h1>Welcome {location.state.login_name}!</h1>
      </div>
    </div>
  );
};

export default Dashboard;
