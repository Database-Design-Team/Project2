import React, { useState, useEffect } from "react";
import "./Feed.scss";
import { useHistory } from "react-router-dom";
import axios from "axios";
import { useStateValue } from "../state";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faPlay,
  faPlusCircle,
  faThumbsUp,
  faThumbsDown,
} from "@fortawesome/fontawesome-free-solid";

const Feed = (props) => {
  const [{ currentSong, credentials }, dispatch] = useStateValue();
  const [list, setList] = useState({});
  let history = useHistory();
  useEffect(() => {
    const abortController = new AbortController();
    // const signal = abortController.signal;

    axios({
      url: "/getAllSongs",
      method: "GET",
      responseType: "json",
    }).then((response) => {
      setList(response.data);
    });
    return function cleanup() {
      abortController.abort();
    };
  }, []);

  const handleClick = (item, song) => {
    const song_id = item;
    let song_name = `Now Playing: ${song.split("|")[0]} ~ ${
      song.split("|")[1]
    }`;
    axios({
      url: "/download-files",
      method: "GET",
      responseType: "blob",
      params: { song_id },
    }).then((response) => {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      dispatch({
        type: "changeSong",
        newSong: { url: url, songName: song_name },
      });
    });
  };

  const handleAddToLibrary = (item) => {
    axios.interceptors.response.use(
      function(response) {
        return response;
      },
      function(error) {
        if (406 === error.response.status) {
          alert("Song is already in your library");
        } else {
          return Promise.reject(error);
        }
      }
    );
    axios({
      url: "/library",
      method: "POST",
      params: { song_id: item, username: credentials.username },
    })
      .then(function(response) {
        if (response.data) {
          alert("Added song to library successfully!");
        }
      })
      .catch(function(error) {
        console.log(error);
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
        <p>Rating</p>
        <p>Add to Library</p>
      </div>
      <ul className="feed-grid-container">
        {Object.keys(list)
          .reverse()
          .map((item, i) => (
            <li className="feed-element-container" key={i}>
              <FontAwesomeIcon
                className="fa-icon play"
                icon={faPlay}
                onClick={() => handleClick(item, list[item])}
              />
              <p>{list[item].split("|")[1]}</p>
              <p>{list[item].split("|")[0]}</p>
              <div className="rating-container">
                <FontAwesomeIcon className="fa-icon up" icon={faThumbsUp} />
                <FontAwesomeIcon className="fa-icon down" icon={faThumbsDown} />
              </div>
              <FontAwesomeIcon
                className="fa-icon add"
                icon={faPlusCircle}
                onClick={() => handleAddToLibrary(item)}
              />
            </li>
          ))}
      </ul>
    </div>
  );
};

export default Feed;
