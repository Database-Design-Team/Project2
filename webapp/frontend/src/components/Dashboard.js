import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useStateValue } from "../state";
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
    //TODO: Flush global state.
    history.push(`/`);
  };

  const [{ credentials, currentSong }, dispatch] = useStateValue();

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
        <div>{`${currentSong.songName}`}</div>
        <div className="btn-group">
          <div>
            <button className="btn btn-sign-out" onClick={handleSignOut}>
              Sign out
            </button>
          </div>
        </div>
      </div>
      <div className="sidebar-container">
        <ul className="sidenav-container">
          <li className="nav-item-container">
            <FontAwesomeIcon
              className="navi"
              icon={faHome}
              onClick={() => changePage("Home")}
            />
            <p>Home</p>
          </li>
          <li className="nav-item-container">
            <FontAwesomeIcon
              className="navi"
              icon={faListUl}
              onClick={() => changePage("Feed")}
            />
            <p>Feed</p>
          </li>
          <li className="nav-item-container">
            <FontAwesomeIcon
              className="navi"
              icon={faMusic}
              onClick={() => changePage("Songs")}
            />
            <p>Library</p>
          </li>
          <li className="nav-item-container">
            <FontAwesomeIcon
              className="navi"
              icon={faIndent}
              onClick={() => changePage("Playlists")}
            />
            <p>Playlists</p>
          </li>
          <li className="nav-item-container">
            <FontAwesomeIcon
              className="navi"
              icon={faUpload}
              onClick={() => changePage("Upload")}
            />
            <p>Upload</p>
          </li>
          <li className="nav-item-container">
            <FontAwesomeIcon
              className="navi"
              icon={faUsers}
              onClick={() => changePage("Artists")}
            />
            <p>Artists</p>
          </li>
        </ul>
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
