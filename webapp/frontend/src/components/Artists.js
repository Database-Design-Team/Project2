import React from "react";
import "./Artists.scss";
import { useStateValue } from "../state";
import Modali, { useModali } from "modali";
import CreateArtist from "./CreateArtist";

const Artists = (props) => {
  const [{ credentials }] = useStateValue();
  const [createArtistModal, toggleCreateArtistModal] = useModali({
    animated: true,
  });

  return (
    <div>
      <div className="artists-header-container">
        <p>Find other artists or become one</p>
        <button className="btn btn--1 btnCA" onClick={toggleCreateArtistModal}>
          Create Artist
        </button>
        <Modali.Modal {...createArtistModal}>
          <CreateArtist />
        </Modali.Modal>
      </div>
      <div className="artists-container">
        <div className="current-artists-container"></div>
      </div>
    </div>
  );
};

export default Artists;
