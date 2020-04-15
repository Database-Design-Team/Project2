import React from "react";
import "./Feed.scss";
import axios from "axios";

const Feed = (props) => {
  const handleClick = () => {
    axios({
      url: "/download-files",
      method: "GET",
      responseType: "blob", // important
    }).then((response) => {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "newsong.mp3");
      document.body.appendChild(link);
      link.click();
    });
  };

  return (
    <div>
      <h1>Download a song</h1>
      <button onClick={handleClick}>press me</button>
    </div>
  );
};

export default Feed;
