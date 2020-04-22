import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Artists.scss";
import { useStateValue } from "../state";
import Modali, { useModali } from "modali";
import CreateArtist from "./CreateArtist";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMinusCircle } from "@fortawesome/fontawesome-free-solid";

const Artists = (props) => {
  const [{ credentials }] = useStateValue();
  const [createArtistModal, toggleCreateArtistModal] = useModali({
    animated: true,
  });
  const [artistList, setArtistList] = useState({});

  useEffect(() => {
    const abortController = new AbortController();

    axios({
      url: "/getAllArtists",
      method: "GET",
      responseType: "json",
    })
      .then((response) => {
        console.log(response.data);
        setArtistList(response.data);
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
      url: "/remove-artist",
      method: "DELETE",
      params: { artist_id: artist_id.item },
    })
      .then(function(response) {
        if (response.data) {
          alert("Removed Artist");
          axios({
            url: "/getAllArtists",
            method: "GET",
            responseType: "json",
          })
            .then((response) => {
              console.log(response.data);
              setArtistList(response.data);
            })
            .catch(function(error) {
              console.error(error);
            });
        }
      })
      .catch(function(error) {
        console.log(error);
      });
  };

  return (
    <div>
      <div className="artists-header-container">
        <p>Add or Delete Artists</p>
        <button className="btn btn--1 btnCA" onClick={toggleCreateArtistModal}>
          Create Artist
        </button>
        <Modali.Modal {...createArtistModal}>
          <CreateArtist />
        </Modali.Modal>
      </div>
      <div className="artists-list-header-container">
        <p>Artist</p>
        <p>Date Formed</p>
        <p>Remove Artist</p>
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
                  icon={faMinusCircle}
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
