import React, { useState } from "react";
import { useHistory, useLocation } from "react-router-dom";
import SideNav, { NavItem } from "@trendmicro/react-sidenav";

import Feed from "./Feed";
import Home from "./Home";
import Songs from "./Songs";
import Playlists from "./Playlists";
import MusicPlayer from "./MusicPlayer";
import Upload from "./Upload";
import "./Dashboard.scss";

const Dashboard = props => {
  let history = useHistory();
  const location = useLocation();
  const handleSignOut = () => {
    history.push(`/`);
  };

  const components = {
    Home: Home,
    Feed: Feed,
    Songs: Songs,
    Playlists: Playlists,
    Upload: Upload
  };

  const [page, changePage] = useState("Home");

  function DynamicComponent(props) {
    const SelectPage = components[props.page];
    return <SelectPage />;
  }

  return (
    <div className="dashboard-container">
      <div className="header-container">
        <div className="home-container">Coog Music</div>
        <div className="search-container">
          <input type="text" placeholder="Search" />
          <i className="fa fa-search"></i>
        </div>
        <div className="btn-group">
          <div>
            <button className="btn btn-sign-out" onClick={handleSignOut}>
              Sign out
            </button>
          </div>
        </div>
      </div>
      <div className="sidebar-container">
        <SideNav
          onSelect={selected => {
            changePage(selected);
          }}
        >
          <SideNav.Nav className="sidenav-container" defaultSelected="home">
            <NavItem eventKey="Home">Home</NavItem>
            <NavItem eventKey="Feed">Feed</NavItem>
            <NavItem eventKey="Songs">Songs</NavItem>
            <NavItem eventKey="Playlists">Playlists</NavItem>
            <NavItem eventKey="Upload">Upload</NavItem>
          </SideNav.Nav>
        </SideNav>
      </div>
      <div className="dashboard-body-container">
        {/* <h1>Welcome {location.state.login_name}!</h1> */}

        <div className="home-content-container">
          <h1>{page}</h1>
          <div className="content-body-container">
            <DynamicComponent page={page} />
          </div>
        </div>
      </div>
      <div className="player-container">
        <MusicPlayer />
      </div>
    </div>
  );
};

export default Dashboard;
