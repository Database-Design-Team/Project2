import React, { useState, useEffect } from "react";
import "./Songs.scss";
import axios from "axios";
import { useStateValue } from "../state";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlay, faMinusCircle } from "@fortawesome/fontawesome-free-solid";

const Songs = (props) => {
  const [{ currentSong, credentials }, dispatch] = useStateValue();
  const [list, setList] = useState({});
  useEffect(() => {
    const abortController = new AbortController();
    // const signal = abortController.signal;

    axios({
      url: "/library",
      method: "GET",
      responseType: "json",
      params: { username: credentials.username },
    }).then((response) => {
      setList(response.data);
    });
    return function cleanup() {
      abortController.abort();
    };
  }, []);

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

  const handleDeleteFromLibrary = (item) => {
    axios({
      url: "/library-delete",
      method: "DELETE",
      params: {
        song_id: item,
      },
    }).then((response) => {
      axios({
        url: "/library",
        method: "GET",
        responseType: "json",
        params: { username: credentials.username },
      }).then((response) => {
        setList(response.data);
      });
    });
  };

  return (
    <div className="feed-container">
      <div className="feed-title-container">
        <p>Here are the songs you've saved.</p>
      </div>
      <div className="playlist-headers-container">
        <p>Play</p>
        <p>Title</p>
        <p>Artist</p>
        <p>Remove</p>
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
              <p>{list[item].split("|")[1]}</p>
              <p>{list[item].split("|")[0]}</p>
              <FontAwesomeIcon
                className="fa-icon add"
                icon={faMinusCircle}
                onClick={() => handleDeleteFromLibrary(item)}
              />
            </li>
          ))}
      </ul>
    </div>
  );
};

export default Songs;
