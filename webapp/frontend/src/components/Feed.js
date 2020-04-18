import React, { useState, useEffect } from "react";
import "./Feed.scss";
import axios from "axios";
import { useStateValue } from "../state";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlay, faPlusCircle } from "@fortawesome/fontawesome-free-solid";

const Feed = (props) => {
  const [{ currentSong }, dispatch] = useStateValue();
  const [list, setList] = useState({});
  useEffect(() => {
    axios({
      url: "/getAllSongs",
      method: "GET",
      responseType: "json", // important
    }).then((response) => {
      setList(response.data);
    });
  });

  const handleClick = (item) => {
    const song_id = item.item;
    axios({
      url: "/download-files",
      method: "GET",
      responseType: "blob",
      params: { song_id },
    }).then((response) => {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      dispatch({
        type: "changeSong",
        newSong: { url: url },
      });
    });
  };

  return (
    <div className="feed-container">
      <div className="feed-title-container">
        <p>Stream the latest songs from all of our users.</p>
      </div>
      <div className="playlist-headers-container">
        <p>Play</p>
        <p>Title</p>
        <p>Artist</p>
        <p>Add to Playlist</p>
      </div>
      <ul className="feed-grid-container">
        {Object.keys(list)
          .reverse()
          .map((item, i) => (
            <li className="feed-element-container" key={i}>
              <FontAwesomeIcon
                className="fa-icon play"
                icon={faPlay}
                onClick={() => handleClick({ item })}
              />
              <p>{list[item]}</p>
              <p>{"Artist"}</p>
              <FontAwesomeIcon className="fa-icon add" icon={faPlusCircle} />
            </li>
          ))}
      </ul>
    </div>
  );
};

export default Feed;
