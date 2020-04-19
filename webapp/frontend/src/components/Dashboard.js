import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import SideNav, { NavItem } from "@trendmicro/react-sidenav";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faHome,
  faListUl,
  faMusic,
  faUpload,
  faIndent,
  faUsers,
} from "@fortawesome/fontawesome-free-solid";

import Feed from "./Feed";
import Home from "./Home";
import Songs from "./Songs";
import Playlists from "./Playlists";
import MusicPlayer from "./MusicPlayer";
import Upload from "./Upload";
import Artists from "./Artists";
import "./Dashboard.scss";

const Dashboard = (props) => {
  let history = useHistory();
  const handleSignOut = () => {
    history.push(`/`);
  };

  const components = {
    Home: Home,
    Feed: Feed,
    Songs: Songs,
    Playlists: Playlists,
    Upload: Upload,
    Artists: Artists,
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
          onSelect={(selected) => {
            changePage(selected);
          }}
        >
          <SideNav.Nav className="sidenav-container" defaultSelected="home">
            <NavItem className="nav-item-container" eventKey="Home">
              <FontAwesomeIcon className="navi" icon={faHome} />
              {"Home"}
            </NavItem>
            <NavItem className="nav-item-container" eventKey="Feed">
              <FontAwesomeIcon className="navi" icon={faListUl} />
              {" Feed"}
            </NavItem>
            <NavItem className="nav-item-container" eventKey="Songs">
              <FontAwesomeIcon className="navi" icon={faMusic} />
              {" Songs"}
            </NavItem>
            <NavItem className="nav-item-container" eventKey="Playlists">
              <FontAwesomeIcon className="navi" icon={faIndent} />
              {" Playlists"}
            </NavItem>
            <NavItem className="nav-item-container" eventKey="Upload">
              <FontAwesomeIcon className="navi" icon={faUpload} />
              {" Upload"}
            </NavItem>
            <NavItem className="nav-item-container" eventKey="Artists">
              <FontAwesomeIcon className="navi" icon={faUsers} />
              {" Artists"}
            </NavItem>
          </SideNav.Nav>
        </SideNav>
      </div>
      <div className="dashboard-body-container">
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
