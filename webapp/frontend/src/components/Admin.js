import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useStateValue } from "../state";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFlag, faMusic, faUsers } from "@fortawesome/fontawesome-free-solid";

import AdminSongs from "./AdminSongs";
import MusicPlayer from "./MusicPlayer";
import AdminArtists from "./AdminArtists";
import Reports from "./Reports";
import "./Dashboard.scss";

const Admin = (props) => {
  let history = useHistory();
  const handleSignOut = () => {
    //TODO: Flush global state.
    history.push(`/`);
  };

  const [{ currentSong }] = useStateValue();

  const components = {
    Reports: Reports,
    AdminSongs: AdminSongs,
    AdminArtists: AdminArtists,
  };

  const [page, changePage] = useState("Reports");

  function DynamicComponent(props) {
    const SelectPage = components[props.page];
    return <SelectPage />;
  }

  return (
    <div className="dashboard-container">
      <div className="header-container">
        <div className="home-container">Coog Music</div>
        {/* <div className="search-container">
          <input type="text" placeholder="Search" />
        </div> */}
        {/* <div className="now-playing-container">{`${currentSong.songName}`}</div> */}
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
              icon={faFlag}
              onClick={() => changePage("Reports")}
            />
            <p>Reports</p>
          </li>
          <li className="nav-item-container">
            <FontAwesomeIcon
              className="navi"
              icon={faMusic}
              onClick={() => changePage("AdminSongs")}
            />
            <p>Songs</p>
          </li>
          <li className="nav-item-container">
            <FontAwesomeIcon
              className="navi"
              icon={faUsers}
              onClick={() => changePage("AdminArtists")}
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
    </div>
  );
};

export default Admin;
