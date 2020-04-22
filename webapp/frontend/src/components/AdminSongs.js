import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import "./AdminSongs.scss";
import ChangeSongTitle from "./ChangeSongTitle";
import Modali, { useModali } from "modali";
import axios from "axios";
import { useStateValue } from "../state";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMinusCircle } from "@fortawesome/fontawesome-free-solid";

const AdminSongs = (props) => {
  const [{ credentials }, dispatch] = useStateValue();
  const { handleSubmit, register, errors } = useForm();
  const [changeSongTitleModal, toggleChangeSongTitleModal] = useModali({
    animated: true,
    onHide: () => {
      axios({
        url: "/getAllSongs",
        method: "GET",
        responseType: "json",
      }).then((response) => {
        setList(response.data);
      });
    },
  });
  const [list, setList] = useState({});
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

  const handleDeleteSong = (song_id) => {
    axios({
      url: "/delete-song",
      method: "PUT",
      params: {
        song_id: song_id,
      },
    }).then((response) => {
      axios({
        url: "/getAllSongs",
        method: "GET",
        responseType: "json",
      }).then((response) => {
        setList(response.data);
      });
    });
  };

  return (
    <div>
      <div className="adminartists-header-container">
        <p>Edit songs.</p>
        <button
          className="btn btn--1 btnCA"
          onClick={toggleChangeSongTitleModal}
        >
          Edit Song Title
        </button>
        <Modali.Modal {...changeSongTitleModal}>
          <ChangeSongTitle />
        </Modali.Modal>
      </div>
      <div className="adminartists-list-header-container">
        <p>Song ID</p>
        <p>Title</p>
        <p>Artist</p>
        <p>Delete Song</p>
      </div>
      <div className="artists-container">
        <ul className="feed-grid-container">
          {Object.keys(list)
            .reverse()
            .map((item, i) => (
              <li className="admin-feed-element-container" key={i}>
                <p>{item}</p>
                <p>{list[item].split("|")[1]}</p>
                <p>{list[item].split("|")[0]}</p>
                <FontAwesomeIcon
                  className="fa-icon add"
                  icon={faMinusCircle}
                  onClick={() => handleDeleteSong(item)}
                />
              </li>
            ))}
        </ul>
      </div>
    </div>
  );
};

export default AdminSongs;
