import React from "react";
import { useHistory } from "react-router-dom";

function Admin() {
  let history = useHistory();
  const handleSignOut = () => {
    //TODO: Flush global state.
    history.push(`/`);
  };

  return (
    <div className="dashboard-container">
      <div className="header-container">
        <div className="home-container">Coog Music</div>
        <div className="search-container">
          <input type="text" placeholder="Search" />
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
}

export default Admin;
