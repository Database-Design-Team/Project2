import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Artists.scss";
import { useStateValue } from "../state";
import Modali, { useModali } from "modali";
import CreateArtist from "./CreateArtist";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlusCircle } from "@fortawesome/fontawesome-free-solid";

const Artists = (props) => {
  const [{ credentials }] = useStateValue();
  // const [{ artistList }, dispatch] = useStateValue();
  const [createArtistModal, toggleCreateArtistModal] = useModali({
    animated: true,
  });
  const [artistList, setArtistList] = useState({});

  useEffect(() => {
    const abortController = new AbortController();
    // const signal = abortController.signal;

    axios({
      url: "/getAllArtists",
      method: "GET",
      responseType: "json",
    })
      .then((response) => {
        console.log(response.data);
        setArtistList(response.data);
        // dispatch({
        //   type: "changeArtistList",
        //   newArtists: { artists: response.data },
        // });
      })
      .catch(function(error) {
        console.error(error);
      });

    return function cleanup() {
      abortController.abort();
    };
  }, []);

  const handleClick = (artist_id) => {
    axios({
      url: "/join-artist",
      method: "POST",
      params: { artist_id: artist_id.item, username: credentials.username },
    })
      .then(function(response) {
        if (response.data) {
          alert("Joined artist successfully");
        }
      })
      .catch(function(error) {
        console.log(error);
      });
  };

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
        <ul className="current-artists-container">
          {Object.keys(artistList)
            .reverse()
            .map((item, i) => (
              <li className="current-artists-element-container" key={i}>
                <p>{Object.keys(artistList[item])}</p>
                <p>{Object.values(artistList[item])}</p>
                <FontAwesomeIcon
                  className="fa-icon add"
                  icon={faPlusCircle}
                  onClick={() => handleClick({ item })}
                />
              </li>
            ))}
        </ul>
      </div>
    </div>
  );
};

export default Artists;

//id
//name
//date formed

{
  /* <div>
  <p>{item}</p>
  <p>{Object.keys(artistList[item])}</p>
  <p>{Object.values(artistList[item])}</p>
  <FontAwesomeIcon className="fa-icon add" icon={faPlusCircle} />
</div> */
}
