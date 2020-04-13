import React from "react";
import { useHistory } from "react-router-dom";
import "./Dashboard.scss";

const Dashboard = () => {
  let history = useHistory();
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
    </div>
  );
};

export default Dashboard;
