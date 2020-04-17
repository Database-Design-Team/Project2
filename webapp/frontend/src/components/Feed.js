import React, { useState, useEffect } from "react";
import "./Feed.scss";
import axios from "axios";
import { useStateValue } from "../state";

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
      {Object.keys(list).map((item, i) => (
        <div className="feed-element-container" key={i}>
          <h2>{list[item].split(".")[0]}</h2>
          <button onClick={() => handleClick({ item })}>Play</button>
        </div>
      ))}
    </div>
  );
};

export default Feed;
