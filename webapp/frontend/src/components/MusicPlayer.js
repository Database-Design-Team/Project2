import React from "react";
import "./MusicPlayer.scss";
import ReactPlayer from "react-player";
import { useStateValue } from "../state";

const MusicPlayer = (props) => {
  const [{ currentSong }] = useStateValue();

  return (
    <div>
      <ReactPlayer
        url={currentSong.url}
        playing
        controls
        width="100%"
        height="50px"
      />
    </div>
  );
};

export default MusicPlayer;
